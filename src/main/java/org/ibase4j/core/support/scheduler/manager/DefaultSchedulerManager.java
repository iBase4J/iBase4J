package org.ibase4j.core.support.scheduler.manager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.model.scheduler.TaskScheduled;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.JobListener;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.InitializingBean;

/**
 * 默认的定时任务管理器
 * 
 * @author ShenHuaJie
 * @version 2016年5月27日 上午10:28:26
 */
public class DefaultSchedulerManager implements SchedulerManager, InitializingBean {
	private Logger logger = LogManager.getLogger(this.getClass());

	private Scheduler scheduler;

	private List<TriggerLoader> triggerLoaders;

	private List<JobListener> jobListeners;

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	public void setTriggerLoaders(List<TriggerLoader> triggerLoaders) {
		this.triggerLoaders = triggerLoaders;
	}

	public void setJobListeners(List<JobListener> jobListeners) {
		this.jobListeners = jobListeners;
	}

	// 调度初始化入口
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

		// 根据配置的初始化装载
		if (this.triggerLoaders != null && this.triggerLoaders.size() > 0) {
			if (logger.isInfoEnabled()) {
				logger.info("Initing task scheduler[" + this.scheduler.getSchedulerName() + "] , trigger loaders size ："
						+ this.triggerLoaders.size());
			}
			for (TriggerLoader loader : this.triggerLoaders) {
				if (logger.isInfoEnabled()) {
					logger.info("Initing triggerLoader[" + loader.getClass().getName() + "].");
				}
				Map<Trigger, JobDetail> loadResultMap = loader.loadTriggers();
				if (loadResultMap != null) {
					for (Entry<Trigger, JobDetail> entry : loadResultMap.entrySet()) {
						this.addJobDetail(entry.getValue());
						this.addTrigger(entry.getKey());
					}
					if (logger.isInfoEnabled()) {
						logger.info("Initing triggerLoader[" + loader.getClass().getName() + "] end .");
					}
				} else {
					logger.warn("No triggers loaded by triggerLoader[" + loader.getClass().getName() + "].");
				}
			}
		} else {
			logger.warn("No TriggerLoader for initing.");
		}
	}

	private void addTrigger(Trigger trigger) {
		Trigger oldTrigger = null;
		try {
			oldTrigger = scheduler.getTrigger(trigger.getKey());
		} catch (Exception e) {
		}
		try {
			if (oldTrigger == null) {
				if (logger.isInfoEnabled()) {
					logger.info("Try to add trigger : " + trigger);
				}
				scheduler.scheduleJob(trigger);
				if (!trigger.getJobDataMap().getBoolean("enable")) {
					scheduler.pauseTrigger(trigger.getKey());
				}
			} else {
				updateTrigger(trigger);
			}
		} catch (SchedulerException e) {
			logger.error("Try to add trigger : " + trigger + "  cause error : ", e);
		}
	}

	private void updateTrigger(Trigger trigger) {
		Trigger oldTrigger = null;
		try {
			oldTrigger = scheduler.getTrigger(trigger.getKey());
		} catch (Exception e) {
		}
		try {
			if (oldTrigger != null) {
				if (logger.isInfoEnabled()) {
					logger.info("Try to update trigger : " + trigger);
				}
				scheduler.rescheduleJob(trigger.getKey(), trigger);
				if (!trigger.getJobDataMap().getBoolean("enable")) {
					scheduler.pauseTrigger(trigger.getKey());
				}
			} else {
				logger.warn("Can't update trigger : " + trigger);
			}
		} catch (SchedulerException e) {
			logger.error("Try to update trigger : " + trigger + ", the old trigger is : "
					+ (oldTrigger == null ? "null" : oldTrigger.toString()) + " cause error : ", e);
		}
	}

	private void addJobDetail(JobDetail jobDetail) {
		JobDetail oldJobDetail = null;
		try {
			oldJobDetail = this.scheduler.getJobDetail(jobDetail.getKey());
		} catch (Exception e) {
		}
		try {
			if (oldJobDetail == null) {
				if (logger.isInfoEnabled()) {
					logger.info("Try to add jobDetail : " + jobDetail);
				}
				this.scheduler.addJob(jobDetail, true);
			} else {
				updateJobDetail(jobDetail);
			}
		} catch (Exception e) {
			logger.error("Try to add jobDetail : " + jobDetail + ", the old jobDetail is : "
					+ (oldJobDetail == null ? "null" : oldJobDetail.toString()) + " cause error : ", e);
		}
	}

	private void updateJobDetail(JobDetail jobDetail) {
		JobDetail oldJobDetail = null;
		try {
			oldJobDetail = this.scheduler.getJobDetail(jobDetail.getKey());
		} catch (Exception e) {
		}
		try {
			if (oldJobDetail != null) {
				if (logger.isInfoEnabled()) {
					logger.info("Try to update oldJobDetail : " + oldJobDetail);
				}
				this.scheduler.addJob(jobDetail, true);
			} else {
				logger.warn("Can't update JobDetail : " + jobDetail);
			}
		} catch (SchedulerException e) {
			logger.error("Try to update JobDetail : " + jobDetail + ", the old JobDetail is : "
					+ (oldJobDetail == null ? "null" : oldJobDetail.toString()) + " cause error : ", e);
		}
	}

	public List<TaskScheduled> getAllJobDetail() {
		List<TaskScheduled> result = new LinkedList<TaskScheduled>();
		try {
			GroupMatcher<JobKey> matcher = GroupMatcher.jobGroupContains("");
			Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
			for (JobKey jobKey : jobKeys) {
				List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
				for (Trigger trigger : triggers) {
					TaskScheduled job = new TaskScheduled();
					job.setTaskName(jobKey.getName());
					job.setTaskGroup(jobKey.getGroup());
					Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
					job.setStatus(triggerState.name());
					if (trigger instanceof CronTrigger) {
						CronTrigger cronTrigger = (CronTrigger) trigger;
						String cronExpression = cronTrigger.getCronExpression();
						job.setTaskCron(cronExpression);
					}
					job.setPreviousFireTime(trigger.getPreviousFireTime());
					job.setNextFireTime(trigger.getNextFireTime());
					job.setDesc(trigger.getJobDataMap().getString("desc"));
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

	// 获取运行中任务
	public List<TaskScheduled> getRuningJobDetail() {
		List<TaskScheduled> jobList = null;
		try {
			List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
			jobList = new ArrayList<TaskScheduled>(executingJobs.size());
			for (JobExecutionContext executingJob : executingJobs) {
				TaskScheduled job = new TaskScheduled();
				JobDetail jobDetail = executingJob.getJobDetail();
				JobKey jobKey = jobDetail.getKey();
				Trigger trigger = executingJob.getTrigger();
				job.setTaskName(jobKey.getName());
				job.setTaskGroup(jobKey.getGroup());
				Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
				job.setStatus(triggerState.name());
				if (trigger instanceof CronTrigger) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					job.setTaskCron(cronExpression);
				}
				job.setPreviousFireTime(trigger.getPreviousFireTime());
				job.setNextFireTime(trigger.getNextFireTime());
				job.setDesc(trigger.getJobDataMap().getString("desc"));
				jobList.add(job);
			}
		} catch (Exception e) {
			logger.error("Try to load running JobDetail cause error : ", e);
		}
		return jobList;
	}

	// 暂停任务
	public boolean stopJob(TaskScheduled scheduleJob) {
		try {
			JobKey jobKey = JobKey.jobKey(scheduleJob.getTaskName(), scheduleJob.getTaskGroup());
			scheduler.pauseJob(jobKey);
			return true;
		} catch (Exception e) {
			logger.error("Try to stop Job cause error : ", e);
		}
		return false;
	}

	// 启动任务
	public boolean resumeJob(TaskScheduled scheduleJob) {
		try {
			JobKey jobKey = JobKey.jobKey(scheduleJob.getTaskName(), scheduleJob.getTaskGroup());
			scheduler.resumeJob(jobKey);
			return true;
		} catch (Exception e) {
			logger.error("Try to resume Job cause error : ", e);
		}
		return false;
	}

	// 执行任务
	public boolean runJob(TaskScheduled scheduleJob) {
		try {
			JobKey jobKey = JobKey.jobKey(scheduleJob.getTaskName(), scheduleJob.getTaskGroup());
			scheduler.triggerJob(jobKey);
			return true;
		} catch (Exception e) {
			logger.error("Try to resume Job cause error : ", e);
		}
		return false;
	}

	public boolean refreshScheduler() {
		try {
			// 根据配置的初始化装载
			if (this.triggerLoaders != null && this.triggerLoaders.size() > 0) {
				if (logger.isInfoEnabled()) {
					logger.info("Initing task scheduler[" + this.scheduler.getSchedulerName()
							+ "] , trigger loaders size ：" + this.triggerLoaders.size());
				}
				// 获取原始调度状态
				List<TaskScheduled> scheduleJobs = getAllJobDetail();
				Map<String, String> stateMap = InstanceUtil.newHashMap();
				for (TaskScheduled scheduleJob : scheduleJobs) {
					stateMap.put(scheduleJob.getTaskGroup() + "." + scheduleJob.getTaskName(), scheduleJob.getStatus());
				}
				// 清空调度
				scheduler.clear();
				// 加载调度
				for (TriggerLoader loader : this.triggerLoaders) {
					if (logger.isInfoEnabled()) {
						logger.info("Initing triggerLoader[" + loader.getClass().getName() + "].");
					}
					Map<Trigger, JobDetail> loadResultMap = loader.loadTriggers();
					if (loadResultMap != null) {
						for (Entry<Trigger, JobDetail> entry : loadResultMap.entrySet()) {
							this.addJobDetail(entry.getValue());
							this.addTrigger(entry.getKey());
							JobKey jobKey = entry.getValue().getKey();
							String key = jobKey.getGroup() + "." + jobKey.getName();
							// 新增任务或原来为暂停状态
							if ("PAUSED".equals(stateMap.get(key)) || !stateMap.containsKey(key)) {
								scheduler.pauseJob(jobKey);
							}
						}
						if (logger.isInfoEnabled()) {
							logger.info("Initing triggerLoader[" + loader.getClass().getName() + "] end .");
						}
					} else {
						logger.warn("No triggers loaded by triggerLoader[" + loader.getClass().getName() + "].");
					}
				}
			} else {
				logger.warn("No TriggerLoader for initing.");
			}
			return true;
		} catch (Exception e) {
			logger.error("Try to refresh scheduler cause error : ", e);
		}
		return false;
	}
}
