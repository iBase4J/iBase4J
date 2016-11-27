package org.ibase4j.core.support.scheduler.job;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.core.support.dubbo.ReferenceUtil;
import org.ibase4j.core.support.scheduler.Constants;
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
public class DubboJob implements Job {
    private Logger logger = LogManager.getLogger(this.getClass());
    // 作业接口包名
    private String provider = "org.ibase4j.provider.scheduler.";

    public void execute(JobExecutionContext context) throws JobExecutionException {
        long start = System.currentTimeMillis();
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        if (Constants.ERROR_STATS.equals(jobDataMap.get("taskStatus"))) {
            return;
        }
        ApplicationContext applicationContext = null;
        JobKey jobKey = context.getJobDetail().getKey();
        try {
            applicationContext = (ApplicationContext)context.getScheduler().getContext().get("applicationContext");
            Object refer = ReferenceUtil.refer(applicationContext, provider + jobKey.getGroup());
            refer.getClass().getDeclaredMethod(jobKey.getName()).invoke(refer);
            double time = (System.currentTimeMillis() - start) / 1000.0;
            logger.info("用时：{}s", time);
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }
}
