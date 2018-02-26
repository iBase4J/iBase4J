package org.ibase4j.service;

import com.alibaba.dubbo.config.annotation.Service;

import top.ibase4j.core.support.scheduler.SchedulerService;

/**
 * 定时任务管理
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:59
 */
@Service(interfaceClass = ISchedulerService.class)
public class SchedulerServiceImpl extends SchedulerService implements ISchedulerService {
}
