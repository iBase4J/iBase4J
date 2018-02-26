package org.ibase4j.service.impl;

import org.ibase4j.service.ISchedulerService;

import com.alibaba.dubbo.config.annotation.Service;
import com.weibo.api.motan.config.springsupport.annotation.MotanService;

import top.ibase4j.core.support.scheduler.SchedulerService;

/**
 * 定时任务管理
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:59
 */
@Service(interfaceClass = ISchedulerService.class)
@MotanService(interfaceClass = ISchedulerService.class)
public class SchedulerServiceImpl extends SchedulerService implements ISchedulerService {
}
