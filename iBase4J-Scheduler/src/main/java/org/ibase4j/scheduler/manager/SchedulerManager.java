package org.ibase4j.scheduler.manager;

import java.util.List;

import org.ibase4j.mybatis.scheduler.model.TaskSchedulerBean;
import org.ibase4j.scheduler.trigger.TriggerLoader;
import org.quartz.JobDetail;
import org.quartz.Trigger;

/**
 * 调度器管理
 * @author liujianchu
 *
 */
public interface SchedulerManager {
    public List<TriggerLoader> getTriggerLoaders();

    public void addTrigger(Trigger trigger);

    public void removeTrigger(Trigger trigger);

    public void updateTrigger(Trigger trigger);

    public void addTriggers(List<Trigger> triggers);

    public void addJobDetail(JobDetail jobDetail);

    public void removeJobDetail(JobDetail jobDetail);

    public void updateJobDetail(JobDetail jobDetail);

    public void addJobDetails(List<JobDetail> jobDetails);

    public List<Trigger> getAllTriggers();

    public List<TaskSchedulerBean> getAllJobDetail();

    public JobDetail getJobDetailByTriggerName(Trigger trigger);

    /** 获取运行中任务 */
    public List<TaskSchedulerBean> getRuningJobDetail();

    /** 暂停任务 */
    public boolean stopJob(TaskSchedulerBean scheduleJob);

    /** 恢复任务 */
    public boolean resumeJob(TaskSchedulerBean scheduleJob);

    /** 运行任务 */
    public boolean runJob(TaskSchedulerBean scheduleJob);

    /** 刷新调度(新增任务为暂停状态) */
    public boolean refreshScheduler();
}
