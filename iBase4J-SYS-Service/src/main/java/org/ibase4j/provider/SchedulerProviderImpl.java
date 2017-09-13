package org.ibase4j.provider;

import org.ibase4j.core.support.scheduler.SchedulerService;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * 定时任务管理
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:59
 */
@Service(interfaceClass = ISchedulerProvider.class)
public class SchedulerProviderImpl extends SchedulerService implements ISchedulerProvider {

}
