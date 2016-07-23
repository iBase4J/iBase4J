package org.ibase4j.core.support.scheduler.task;

import org.ibase4j.core.support.scheduler.Constants;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.springframework.context.ApplicationContext;

/**
 * 反射调用作业
 * 
 * @author ShenHuaJie
 * @version 2016年5月27日 下午4:30:46
 */
public class InvokeTask implements Job {
	// 作业接口包名
	private String basePackage = "org.ibase4j.service.task";

	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		if (Constants.ERROR_STATS.equals(jobDataMap.get("taskStatus"))) {
			return;
		}
		ApplicationContext applicationContext = null;
		JobKey jobKey = context.getJobDetail().getKey();
		try {
			applicationContext = (ApplicationContext) context.getScheduler().getContext().get("applicationContext");
			Object refer = applicationContext.getBean(basePackage + jobKey.getGroup());
			refer.getClass().getDeclaredMethod(jobKey.getName()).invoke(refer);
		} catch (Exception e) {
			throw new JobExecutionException(e);
		}
	}

}
