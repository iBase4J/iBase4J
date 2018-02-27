package org.ibase4j.service.impl;

import org.ibase4j.service.ISchedulerService;

import com.alibaba.dubbo.config.annotation.Service;
import com.weibo.api.motan.config.springsupport.annotation.MotanService;

import top.ibase4j.core.support.scheduler.SchedulerService;

/**
 * @author ShenHuaJie
 * @version 2016年7月1日 上午11:34:59
 */
@Service(interfaceClass = ISchedulerService.class)
@MotanService(interfaceClass = ISchedulerService.class)
public class ScheduledServiceImpl extends SchedulerService implements ISchedulerService {

}
