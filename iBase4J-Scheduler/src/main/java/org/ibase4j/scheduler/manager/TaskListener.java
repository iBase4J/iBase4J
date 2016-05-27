package org.ibase4j.scheduler.manager;

import java.sql.Timestamp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.mybatis.generator.model.TaskFireLog;
import org.ibase4j.mybatis.generator.model.TaskScheduler;
import org.ibase4j.scheduler.Constants;
import org.ibase4j.service.SchedulerService;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.JobListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;

/**
 * 日志监听器
 * 
 * @author ShenHuaJie
 * @version 2016年5月27日 下午4:31:31
 */
public class TaskListener implements JobListener {
	private Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	private SchedulerService schedulerService;

	public String getName() {
		return "taskListener";
	}

	public void jobExecutionVetoed(JobExecutionContext context) {

	}

	public void jobToBeExecuted(JobExecutionContext context) {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		JobKey jobKey = context.getJobDetail().getKey();
		String groupName = jobKey.getGroup();
		String jobName = jobKey.getName();
		if (logger.isInfoEnabled()) {
			logger.info("Listened job : " + groupName + "." + jobName + " prepared to start.");
		}
		Timestamp start = new Timestamp(System.currentTimeMillis());
		TaskFireLog log = new TaskFireLog();
		log.setStartTime(start);
		log.setGroupName(groupName);
		log.setTaskName(jobName);
		log.setStatus(Constants.INIT_STATS);
		try {
			schedulerService.updateLog(log);
			jobDataMap.put(Constants.JOB_LOG, log);
		} catch (Exception e) {
			logger.error("Save TaskRunLog cause error. The log object is : " + JSON.toJSONString(log), e);
		}
		TaskScheduler taskScheduler = schedulerService.getSchedulerById(jobDataMap.getInt("id"));
		taskScheduler.setTaskFireTime(context.getFireTime());
		taskScheduler.setTaskNextFireTime(context.getNextFireTime());
		schedulerService.updateScheduler(taskScheduler);
	}

	public void jobWasExecuted(JobExecutionContext context, JobExecutionException exp) {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		String groupName = context.getJobDetail().getKey().getGroup();
		String jobName = context.getJobDetail().getKey().getName();
		if (logger.isInfoEnabled()) {
			logger.info("Listened job : " + groupName + "." + jobName + " executed.");
		}
		TaskFireLog log = (TaskFireLog) jobDataMap.get(Constants.JOB_LOG);
		Timestamp end = new Timestamp(System.currentTimeMillis());
		log.setEndTime(end);
		if (exp != null) {
			log.setStatus(Constants.ERROR_STATS);
			log.setFireInfo(exp.getMessage());
		} else {
			if (log.getStatus().equals(Constants.INIT_STATS)) {
				log.setStatus(Constants.SUCCESS_STATS);
			}
		}
		try {
			schedulerService.updateLog(log);
		} catch (Exception e) {
			logger.error("Update TaskRunLog cause error. The log object is : " + JSON.toJSONString(log), e);
		}
	}
}
