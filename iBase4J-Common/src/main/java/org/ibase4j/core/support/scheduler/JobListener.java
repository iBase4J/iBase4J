package org.ibase4j.core.support.scheduler;

import java.sql.Timestamp;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.core.Constants.JOBSTATE;
import org.ibase4j.core.support.email.Email;
import org.ibase4j.core.support.mq.QueueSender;
import org.ibase4j.core.util.EmailUtil;
import org.ibase4j.core.util.NativeUtil;
import org.ibase4j.model.TaskFireLog;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;

/**
 * 调度监听器
 * 
 * @author ShenHuaJie
 * @version 2016年5月27日 下午4:31:31
 */
public class JobListener implements org.quartz.JobListener {
	private static Logger logger = LogManager.getLogger(JobListener.class);
	@Autowired
	private SchedulerService schedulerService;
	private QueueSender emailQueueSender;
	// 线程池
	private static ExecutorService executorService = Executors.newCachedThreadPool();
	private static String JOB_LOG = "jobLog";

	public void setEmailQueueSender(QueueSender emailQueueSender) {
		this.emailQueueSender = emailQueueSender;
	}

	public String getName() {
		return "taskListener";
	}

	public void jobExecutionVetoed(JobExecutionContext context) {
	}

	// 任务开始前
	public void jobToBeExecuted(final JobExecutionContext context) {
		final JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		String targetObject = jobDataMap.getString("targetObject");
		String targetMethod = jobDataMap.getString("targetMethod");
		if (logger.isInfoEnabled()) {
			logger.info("定时任务开始执行：{}.{}", targetObject, targetMethod);
		}
		// 保存日志
		TaskFireLog log = new TaskFireLog();
		log.setStartTime(context.getFireTime());
		log.setGroupName(targetObject);
		log.setTaskName(targetMethod);
		log.setStatus(JOBSTATE.INIT_STATS);
		log.setServerHost(NativeUtil.getHostName());
		log.setServerDuid(NativeUtil.getDUID());
		schedulerService.updateLog(log);
		jobDataMap.put(JOB_LOG, log);
	}

	// 任务结束后
	public void jobWasExecuted(final JobExecutionContext context, JobExecutionException exp) {
		Timestamp end = new Timestamp(System.currentTimeMillis());
		final JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		String targetObject = jobDataMap.getString("targetObject");
		String targetMethod = jobDataMap.getString("targetMethod");
		if (logger.isInfoEnabled()) {
			logger.info("定时任务执行结束：{}.{}", targetObject, targetMethod);
		}
		// 更新任务执行状态
		final TaskFireLog log = (TaskFireLog) jobDataMap.get(JOB_LOG);
		if (log != null) {
			log.setEndTime(end);
			if (exp != null) {
				logger.error("定时任务失败: [" + targetObject + "." + targetMethod + "]", exp);
				String contactEmail = jobDataMap.getString("contactEmail");
				if (StringUtils.isNotBlank(contactEmail)) {
					String topic = String.format("调度[%s.%s]发生异常", targetMethod, targetMethod);
					sendEmail(new Email(contactEmail, topic, exp.getMessage()));
				}
				log.setStatus(JOBSTATE.ERROR_STATS);
				log.setFireInfo(exp.getMessage());
			} else {
				if (log.getStatus().equals(JOBSTATE.INIT_STATS)) {
					log.setStatus(JOBSTATE.SUCCESS_STATS);
				}
			}
		}
		executorService.submit(new Runnable() {
			public void run() {
				if (log != null) {
					try {
						schedulerService.updateLog(log);
					} catch (Exception e) {
						logger.error("Update TaskRunLog cause error. The log object is : " + JSON.toJSONString(log), e);
					}
				}
			}
		});
	}

	private void sendEmail(final Email email) {
		executorService.submit(new Runnable() {
			public void run() {
				if (emailQueueSender != null) {
					emailQueueSender.send("iBase4J.emailSender", email);
				} else {
					logger.info("将发送邮件至：" + email.getSendTo());
					EmailUtil.sendEmail(email);
				}
			}
		});
	}
}
