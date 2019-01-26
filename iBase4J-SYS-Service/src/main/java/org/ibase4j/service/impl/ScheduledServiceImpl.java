package org.ibase4j.service.impl;

import org.ibase4j.service.SchedulerService;

import com.alibaba.dubbo.config.annotation.Service;

import top.ibase4j.core.support.scheduler.SchedulerServiceImpl;

/**
 * @author ShenHuaJie
 * @version 2016年7月1日 上午11:34:59
 */
@Service(interfaceClass = SchedulerService.class)
public class ScheduledServiceImpl extends SchedulerServiceImpl implements SchedulerService {

}