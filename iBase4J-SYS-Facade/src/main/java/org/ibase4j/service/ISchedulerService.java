/**
 * 
 */
package org.ibase4j.service;

import java.util.List;
import java.util.Map;

import top.ibase4j.core.support.scheduler.TaskScheduled;
import top.ibase4j.model.TaskFireLog;

/**
 * 定时任务管理
 * 
 * @author ShenHuaJie
 * @version 2016年5月15日 上午11:06:49
 */
public interface ISchedulerService {

	/** 获取所有任务 */
	public List<TaskScheduled> getAllTaskDetail();

	/** 执行任务 */
	public void execTask(TaskScheduled taskScheduler);

	/** 启停 */
	public void openTask(TaskScheduled taskScheduler);

	/** 启停 */
	public void closeTask(TaskScheduled taskScheduler);

	/** 删除作业 */
	public void delTask(TaskScheduled taskScheduler);

	public TaskFireLog updateLog(TaskFireLog record);

	public TaskFireLog getFireLogById(Long id);

	public Object queryLog(Map<String, Object> params);

	/** 修改执行计划 */
	public void updateTask(TaskScheduled taskScheduled);
}
