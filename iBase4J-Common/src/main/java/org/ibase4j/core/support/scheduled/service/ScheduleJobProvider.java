/**
 * 
 */
package org.ibase4j.core.support.scheduled.service;

import java.util.List;

import org.ibase4j.core.support.scheduled.ScheduleJob;

/**
 * 定时任务管理
 * 
 * @author ShenHuaJie
 * @version 2016年5月15日 上午11:06:49
 */
public interface ScheduleJobProvider {

	// 获取所有任务
	public List<ScheduleJob> getAllJobDetail();

	// 执行任务
	public boolean execTask(int id);
}
