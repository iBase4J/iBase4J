package org.ibase4j.core.support.scheduler;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.core.exception.BusinessException;
import org.ibase4j.core.support.scheduler.job.BaseJob;
import org.ibase4j.core.support.scheduler.job.StatefulJob;
import org.ibase4j.core.util.DataUtil;
import org.ibase4j.model.scheduler.TaskScheduled;
import org.ibase4j.model.scheduler.TaskScheduled.JobType;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.JobListener;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.InitializingBean;

/**
 * 默认的定时任务管理器
 * 
 * @author ShenHuaJie
 * @version 2016年5月27日 上午10:28:26
 */
public class SchedulerManager implements InitializingBean {
    private Logger logger = LogManager.getLogger(this.getClass());

    private Scheduler scheduler;

    private List<JobListener> jobListeners;

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void setJobListeners(List<JobListener> jobListeners) {
        this.jobListeners = jobListeners;
    }

    public void afterPropertiesSet() throws Exception {
        if (this.jobListeners != null && this.jobListeners.size() > 0) {
            if (logger.isInfoEnabled()) {
                logger.info("Initing task scheduler[" + this.scheduler.getSchedulerName() + "] , add listener size ："
                    + this.jobListeners.size());
            }
            for (JobListener listener : this.jobListeners) {
                if (logger.isInfoEnabled()) {
                    logger.info("Add JobListener : " + listener.getName());
                }
                this.scheduler.getListenerManager().addJobListener(listener);
            }
        }
    }

    public List<TaskScheduled> getAllJobDetail() {
        List<TaskScheduled> result = new LinkedList<TaskScheduled>();
        try {
            GroupMatcher<JobKey> matcher = GroupMatcher.jobGroupContains("");
            Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
            for (JobKey jobKey : jobKeys) {
                JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                for (Trigger trigger : triggers) {
                    TaskScheduled job = new TaskScheduled();
                    job.setTaskName(jobKey.getName());
                    job.setTaskGroup(jobKey.getGroup());
                    TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                    job.setStatus(triggerState.name());
                    if (trigger instanceof CronTrigger) {
                        CronTrigger cronTrigger = (CronTrigger)trigger;
                        String cronExpression = cronTrigger.getCronExpression();
                        job.setTaskCron(cronExpression);
                    }
                    job.setPreviousFireTime(trigger.getPreviousFireTime());
                    job.setNextFireTime(trigger.getNextFireTime());
                    JobDataMap jobDataMap = trigger.getJobDataMap();
                    job.setTaskType(jobDataMap.getString("taskType"));
                    job.setTargetObject(jobDataMap.getString("targetObject"));
                    job.setTargetMethod(jobDataMap.getString("targetMethod"));
                    job.setTaskDesc(jobDetail.getDescription());
                    String jobClass = jobDetail.getJobClass().getSimpleName();
                    if (jobClass.equals("StatefulJob")) {
                        job.setJobType("statefulJob");
                    } else if (jobClass.equals("DefaultJob")) {
                        job.setJobType("job");
                    }
                    result.add(job);
                }
            }
        } catch (Exception e) {
            logger.error("Try to load All JobDetail cause error : ", e);
        }
        return result;
    }

    public JobDetail getJobDetailByTriggerName(Trigger trigger) {
        try {
            return this.scheduler.getJobDetail(trigger.getJobKey());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 新增job
     * 
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    public boolean updateTask(TaskScheduled taskScheduled) {
        String jobGroup = "ds_job";
        String jobName = taskScheduled.getTaskName();
        if (DataUtil.isEmpty(jobName)) {
            jobName = String.valueOf(System.currentTimeMillis());
        }
        String cronExpression = taskScheduled.getTaskCron();
        String targetObject = taskScheduled.getTargetObject();
        String targetMethod = taskScheduled.getTargetMethod();
        String jobDescription = taskScheduled.getTaskDesc();
        String jobType = taskScheduled.getJobType();
        String taskType = taskScheduled.getTaskType();
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("targetObject", targetObject);
        jobDataMap.put("targetMethod", targetMethod);
        jobDataMap.put("taskType", taskType);
        jobDataMap.put("contactEmail", taskScheduled.getContactEmail());

        JobBuilder jobBuilder = null;
        if (JobType.job.equals(jobType)) {
            jobBuilder = JobBuilder.newJob(BaseJob.class);
        } else if (JobType.statefulJob.equals(jobType)) {
            jobBuilder = JobBuilder.newJob(StatefulJob.class);
        }
        if (jobBuilder != null) {
            JobDetail jobDetail = jobBuilder.withIdentity(jobName, jobGroup).withDescription(jobDescription)
                .storeDurably(true).usingJobData(jobDataMap).build();

            Trigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .withIdentity(jobName, jobGroup).withDescription(jobDescription).forJob(jobDetail)
                .usingJobData(jobDataMap).build();

            try {
                JobDetail detail = scheduler.getJobDetail(new JobKey(jobName, jobGroup));
                if (detail == null) {
                    scheduler.scheduleJob(jobDetail, trigger);
                } else {
                    scheduler.addJob(jobDetail, true);
                    scheduler.rescheduleJob(new TriggerKey(jobName, jobGroup), trigger);
                }
                return true;
            } catch (SchedulerException e) {
                logger.error("SchedulerException", "SchedulerException", e);
                throw new BusinessException(e);
            }
        }
        return false;
    }

    /**
     * 暂停所有触发器
     * 
     * @return
     */
    public void pauseAllTrigger() {
        try {
            scheduler.standby();
        } catch (SchedulerException e) {
            logger.error("SchedulerException", "SchedulerException", e);
            throw new BusinessException(e);
        }
    }

    /**
     * 启动所有触发器
     * 
     * @return
     */
    public void startAllTrigger() {
        try {
            if (scheduler.isInStandbyMode()) {
                scheduler.start();
            }
        } catch (SchedulerException e) {
            logger.error("SchedulerException", "SchedulerException", e);
            throw new BusinessException(e);
        }
    }

    // 暂停任务
    public void stopJob(TaskScheduled scheduleJob) {
        try {
            JobKey jobKey = JobKey.jobKey(scheduleJob.getTaskName(), scheduleJob.getTaskGroup());
            scheduler.pauseJob(jobKey);
        } catch (Exception e) {
            logger.error("Try to stop Job cause error : ", e);
            throw new BusinessException(e);
        }
    }

    // 启动任务
    public void resumeJob(TaskScheduled scheduleJob) {
        try {
            JobKey jobKey = JobKey.jobKey(scheduleJob.getTaskName(), scheduleJob.getTaskGroup());
            scheduler.resumeJob(jobKey);
        } catch (Exception e) {
            logger.error("Try to resume Job cause error : ", e);
            throw new BusinessException(e);
        }
    }

    // 执行任务
    public void runJob(TaskScheduled scheduleJob) {
        try {
            JobKey jobKey = JobKey.jobKey(scheduleJob.getTaskName(), scheduleJob.getTaskGroup());
            scheduler.triggerJob(jobKey);
        } catch (Exception e) {
            logger.error("Try to resume Job cause error : ", e);
            throw new BusinessException(e);
        }
    }

    // 删除任务
    public void delJob(TaskScheduled scheduleJob) {
        try {
            JobKey jobKey = JobKey.jobKey(scheduleJob.getTaskName(), scheduleJob.getTaskGroup());
            TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getTaskName(), scheduleJob.getTaskGroup());
            scheduler.pauseTrigger(triggerKey);// 停止触发器
            scheduler.unscheduleJob(triggerKey);// 移除触发器
            scheduler.deleteJob(jobKey);// 删除任务
        } catch (Exception e) {
            logger.error("Try to resume Job cause error : ", e);
            throw new BusinessException(e);
        }
    }
}
