package org.shjr.iplat.service.sys;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.matchers.GroupMatcher;
import org.shjr.iplat.mybatis.sys.model.ScheduleJob;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

@Service
public class SysQuartzService {
	private Logger logger = LogManager.getLogger();

	public List<ScheduleJob> getAllJobDetail() {
		List<ScheduleJob> result = new LinkedList<ScheduleJob>();
		try {
			Map<String, SchedulerFactoryBean> factorys = getFactoryBeans();
			for (SchedulerFactoryBean factoryBean : factorys.values()) {
				Scheduler scheduler = factoryBean.getScheduler();
				GroupMatcher<JobKey> matcher = GroupMatcher.jobGroupContains("");
				Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
				for (JobKey jobKey : jobKeys) {
					List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
					for (Trigger trigger : triggers) {
						ScheduleJob job = new ScheduleJob();
						job.setJobName(jobKey.getName());
						job.setJobGroup(jobKey.getGroup());
						job.setDesc(trigger.getDescription());
						Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
						job.setJobStatus(triggerState.name());
						if (trigger instanceof CronTrigger) {
							CronTrigger cronTrigger = (CronTrigger) trigger;
							String cronExpression = cronTrigger.getCronExpression();
							job.setCronExpression(cronExpression);
						}
						result.add(job);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Try to load All JobDetail cause error : " + e.getMessage(), e);
		}
		return result;
	}

	public boolean openCloseTask(String jobName, String jobGroup, String parameter) {
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		if ("true".equals(parameter)) {
			return resumeJob(jobKey);
		} else if ("false".equals(parameter)) {
			return stopJob(jobKey);
		}
		return false;
	}

	public boolean execTask(String jobName, String jobGroup) {
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		return runJob(jobKey);
	}

	private boolean stopJob(JobKey jobKey) {
		Map<String, SchedulerFactoryBean> factorys = getFactoryBeans();
		for (SchedulerFactoryBean factoryBean : factorys.values()) {
			try {
				Scheduler scheduler = factoryBean.getScheduler();
				scheduler.pauseJob(jobKey);
				return true;
			} catch (Exception e) {
				logger.error("Try to stop Job cause error : " + e.getMessage(), e);
			}
		}
		return false;
	}

	private Map<String, SchedulerFactoryBean> getFactoryBeans() {
		WebApplicationContext app = ContextLoader.getCurrentWebApplicationContext();
		Map<String, SchedulerFactoryBean> factorys = app.getBeansOfType(SchedulerFactoryBean.class);
		return factorys;
	}

	private boolean resumeJob(JobKey jobKey) {
		Map<String, SchedulerFactoryBean> factorys = getFactoryBeans();
		for (SchedulerFactoryBean factoryBean : factorys.values()) {
			try {
				Scheduler scheduler = factoryBean.getScheduler();
				scheduler.resumeJob(jobKey);
				return true;
			} catch (Exception e) {
				logger.error("Try to resume Job cause error : " + e.getMessage(), e);
			}
		}
		return false;
	}

	private boolean runJob(JobKey jobKey) {
		Map<String, SchedulerFactoryBean> factorys = getFactoryBeans();
		for (SchedulerFactoryBean factoryBean : factorys.values()) {
			try {
				Scheduler scheduler = factoryBean.getScheduler();
				scheduler.triggerJob(jobKey);
				return true;
			} catch (Exception e) {
				logger.error("Try to resume Job cause error : " + e.getMessage(), e);
			}
		}
		return false;
	}

}
