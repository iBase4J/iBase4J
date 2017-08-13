/**
 * 
 */
package org.ibase4j.core.support.scheduler.job;

import org.ibase4j.core.base.BaseProvider;
import org.ibase4j.core.base.Parameter;
import org.ibase4j.core.support.scheduler.TaskScheduled.TaskType;
import org.ibase4j.core.util.DubboUtil;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * 默认调度(非阻塞)
 * 
 * @author ShenHuaJie
 * @version 2016年12月29日 上午11:52:32
 */
public class BaseJob implements Job {
	private final Logger logger = LoggerFactory.getLogger(BaseJob.class);

	public void execute(JobExecutionContext context) throws JobExecutionException {
		long start = System.currentTimeMillis();
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		String taskType = jobDataMap.getString("taskType");
		String targetObject = jobDataMap.getString("targetObject");
		String targetMethod = jobDataMap.getString("targetMethod");
		try {
			ApplicationContext applicationContext = (ApplicationContext) context.getScheduler().getContext()
					.get("applicationContext");
			if (TaskType.local.equals(taskType)) {
				Object refer = applicationContext.getBean(targetObject);
				refer.getClass().getDeclaredMethod(targetMethod).invoke(refer);
			} else if (TaskType.dubbo.equals(taskType)) {
				String system = "org.ibase4j.provider.I" + jobDataMap.getString("targetSystem");
				BaseProvider provider = (BaseProvider) DubboUtil.refer(applicationContext, system);
				provider.execute(new Parameter(targetObject, targetMethod));
			}
			double time = (System.currentTimeMillis() - start) / 1000.0;
			logger.info("定时任务[{}.{}]用时：{}s", targetObject, targetMethod, time);
		} catch (Exception e) {
			throw new JobExecutionException(e);
		}
	}
}
