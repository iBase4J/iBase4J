/**
 * 
 */
package org.ibase4j.core.support.scheduler.job;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.core.support.dubbo.ReferenceUtil;
import org.ibase4j.model.scheduler.TaskScheduled.TaskType;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

/**
 * 
 * @author ShenHuaJie
 * @version 2016年12月29日 上午11:52:32
 */
public abstract class BaseJob {
    private Logger logger = LogManager.getLogger(this.getClass());
    private String provider = "org.ibase4j.provider.scheduler.";
    
    public void execute(JobExecutionContext context) throws JobExecutionException {
        long start = System.currentTimeMillis();
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        String taskType = (String)jobDataMap.get("taskType");
        String targetObject = jobDataMap.getString("targetObject");
        String targetMethod = jobDataMap.getString("targetMethod");
        logger.info("定时任务开始执行: [{}.{}]", targetObject, targetMethod);
        try {
            ApplicationContext applicationContext = (ApplicationContext)context.getScheduler().getContext()
                .get("applicationContext");
            if (TaskType.local.equals(taskType)) {
                Object refer = applicationContext.getBean(targetObject);
                refer.getClass().getDeclaredMethod(targetMethod).invoke(refer);
            } else if (TaskType.dubbo.equals(taskType)) {
                Object refer = ReferenceUtil.refer(applicationContext, provider + targetObject);
                refer.getClass().getDeclaredMethod(targetMethod).invoke(refer);
            }
            double time = (System.currentTimeMillis() - start) / 1000.0;
            logger.info("定时任务[{}.{}]用时：{}s", targetObject, targetMethod, time);
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }
}
