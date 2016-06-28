package org.ibase4j.scheduler.manager;

import java.sql.Timestamp;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.core.support.email.Email;
import org.ibase4j.core.support.mq.QueueSender;
import org.ibase4j.core.util.NativeUtil;
import org.ibase4j.model.generator.TaskFireLog;
import org.ibase4j.model.generator.TaskScheduler;
import org.ibase4j.provider.scheduler.SchedulerProvider;
import org.ibase4j.scheduler.Constants;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.JobListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;

/**
 * 调度监听器
 * 
 * @author ShenHuaJie
 * @version 2016年5月27日 下午4:31:31
 */
public class TaskListener implements JobListener {
	private Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	private SchedulerProvider schedulerProvider;
	@Autowired
	private QueueSender queueSender;
	// 发送邮件线程池
	private ExecutorService executorService = Executors.newCachedThreadPool();

	public String getName() {
		return "taskListener";
	}

	public void jobExecutionVetoed(JobExecutionContext context) {

	}

	// 任务开始前
	public void jobToBeExecuted(final JobExecutionContext context) {
		final JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		JobKey jobKey = context.getJobDetail().getKey();
		String groupName = jobKey.getGroup();
		String jobName = jobKey.getName();
		if (logger.isInfoEnabled()) {
			logger.info("Listened job : " + groupName + "." + jobName + " prepared to start.");
		}
		// 保存日志
		final TaskFireLog log = new TaskFireLog();
		log.setStartTime(context.getFireTime());
		log.setGroupName(groupName);
		log.setTaskName(jobName);
		log.setStatus(Constants.INIT_STATS);
		log.setServerHost(NativeUtil.getHostName());
		log.setServerDuid(NativeUtil.getDUID());
		schedulerProvider.updateLog(log);
		jobDataMap.put(Constants.JOB_LOG, log);
	}

	// 任务结束后
	public void jobWasExecuted(final JobExecutionContext context, JobExecutionException exp) {
		Timestamp end = new Timestamp(System.currentTimeMillis());
		final JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		if (Constants.ERROR_STATS.equals(jobDataMap.get("taskStatus"))) {
			return;
		}
		String groupName = context.getJobDetail().getKey().getGroup();
		String jobName = context.getJobDetail().getKey().getName();
		if (logger.isInfoEnabled()) {
			logger.info("Listened job : " + groupName + "." + jobName + " executed.");
		}
		// 更新任务执行状态
		final TaskFireLog log = (TaskFireLog) jobDataMap.get(Constants.JOB_LOG);
		log.setEndTime(end);
		if (exp != null) {
			String contactEmail = jobDataMap.getString("contactEmail");
			if (StringUtils.isNotBlank(contactEmail)) {
				String topic = String.format("调度[%s.%s]发生异常", groupName, jobName);
				sendEmail(new Email(contactEmail, topic, exp.getMessage()));
			}
			log.setStatus(Constants.ERROR_STATS);
			log.setFireInfo(exp.getMessage());
		} else {
			if (log.getStatus().equals(Constants.INIT_STATS)) {
				log.setStatus(Constants.SUCCESS_STATS);
			}
		}
		executorService.submit(new Runnable() {
			public void run() {
				try {
					schedulerProvider.updateLog(log);
				} catch (Exception e) {
					logger.error("Update TaskRunLog cause error. The log object is : " + JSON.toJSONString(log), e);
				}
				// 更新任务执行时间
				TaskScheduler taskScheduler = schedulerProvider.getSchedulerById(jobDataMap.getInt("id"));
				taskScheduler.setTaskPreviousFireTime(context.getFireTime());
				taskScheduler.setTaskNextFireTime(context.getNextFireTime());
				schedulerProvider.updateScheduler(taskScheduler);
			}
		});
	}

	private void sendEmail(final Email email) {
		executorService.submit(new Runnable() {
			public void run() {
				queueSender.send("iBase4J.emailSender", email);
			}
		});
	}
}