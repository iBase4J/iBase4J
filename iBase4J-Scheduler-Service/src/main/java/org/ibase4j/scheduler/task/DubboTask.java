package org.ibase4j.scheduler.task;

import org.ibase4j.core.support.dubbo.ReferenceUtil;
import org.ibase4j.scheduler.Constants;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.springframework.context.ApplicationContext;

/**
 * Dubbo调用远程作业
 * 
 * @author ShenHuaJie
 * @version 2016年5月27日 下午4:30:46
 */
public class DubboTask implements Job {
	// 作业接口包名
	private String provider = "org.ibase4j.provider.scheduler.";

	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		if (Constants.ERROR_STATS.equals(jobDataMap.get("taskStatus"))) {
			return;
		}
		ApplicationContext applicationContext = null;
		JobKey jobKey = context.getJobDetail().getKey();
		try {
			applicationContext = (ApplicationContext) context.getScheduler().getContext().get("applicationContext");
			Object refer = ReferenceUtil.refer(applicationContext, provider + jobKey.getGroup());
			refer.getClass().getDeclaredMethod(jobKey.getName()).invoke(refer);
		} catch (Exception e) {
			throw new JobExecutionException(e);
		}
	}

}
