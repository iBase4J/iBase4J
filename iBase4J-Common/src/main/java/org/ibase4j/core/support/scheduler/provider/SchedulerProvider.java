/**
 * 
 */
package org.ibase4j.core.support.scheduler.provider;

import java.util.List;

import org.ibase4j.core.support.scheduler.TaskScheduler;

/**
 * 定时任务管理
 * 
 * @author ShenHuaJie
 * @version 2016年5月15日 上午11:06:49
 */
public interface SchedulerProvider {

	/** 获取所有任务 */
	public List<TaskScheduler> getAllTaskDetail();

	/** 执行任务 */
	public boolean execTask(String taskGroup, String taskName);

	/** 启停 */
	public boolean openCloseTask(String taskGroup, String taskName, String status);
}
