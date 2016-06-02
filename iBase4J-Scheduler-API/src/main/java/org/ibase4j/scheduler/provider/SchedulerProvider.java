/**
 * 
 */
package org.ibase4j.scheduler.provider;

import java.util.List;
import java.util.Map;

import org.ibase4j.mybatis.generator.model.TaskFireLog;
import org.ibase4j.mybatis.generator.model.TaskGroup;
import org.ibase4j.mybatis.generator.model.TaskScheduler;
import org.ibase4j.mybatis.scheduler.model.TaskSchedulerBean;
import org.ibase4j.scheduler.TaskScheduled;

import com.github.pagehelper.PageInfo;

/**
 * 定时任务管理
 * 
 * @author ShenHuaJie
 * @version 2016年5月15日 上午11:06:49
 */
public interface SchedulerProvider {

	/** 获取所有任务 */
	public List<TaskScheduled> getAllTaskDetail();

	/** 执行任务 */
	public boolean execTask(String taskGroup, String taskName);

	/** 启停 */
	public boolean openCloseTask(String taskGroup, String taskName, String status);

	public TaskGroup getGroupById(Integer id);

	public void updateGroup(TaskGroup record);

	public PageInfo<TaskGroup> queryGroup(Map<String, Object> params);

	public TaskScheduler getSchedulerById(Integer id);

	public void updateScheduler(TaskScheduler record);

	public PageInfo<TaskSchedulerBean> queryScheduler(Map<String, Object> params);

	public TaskFireLog getFireLogById(Integer id);

	public PageInfo<TaskFireLog> queryLog(Map<String, Object> params);
}
