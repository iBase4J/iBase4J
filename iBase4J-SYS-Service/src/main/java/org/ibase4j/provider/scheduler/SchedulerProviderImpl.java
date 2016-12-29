package org.ibase4j.provider.scheduler;

import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.core.support.scheduler.SchedulerService;

/**
 * 定时任务管理
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:59
 */
@DubboService(interfaceClass = SchedulerProvider.class)
public class SchedulerProviderImpl extends SchedulerService implements SchedulerProvider {
    
}
