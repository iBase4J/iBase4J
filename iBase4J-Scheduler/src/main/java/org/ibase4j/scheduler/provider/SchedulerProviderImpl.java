package org.ibase4j.scheduler.provider;

import java.util.List;

import org.ibase4j.core.support.dubbo.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.core.support.scheduler.TaskScheduler;
import org.ibase4j.core.support.scheduler.provider.SchedulerProvider;
import org.ibase4j.scheduler.manager.SchedulerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

/**
 * 定时任务管理
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:59
 */
@DubboService(interfaceClass = SchedulerProvider.class)
public class SchedulerProviderImpl extends BaseProviderImpl<TaskScheduler> implements SchedulerProvider {
	@Autowired
	private SchedulerManager schedulerManager;

	// 获取所有作业
	@Cacheable
	public List<TaskScheduler> getAllTaskDetail() {
		return schedulerManager.getAllJobDetail();
	}

	// 执行作业
	public boolean execTask(String taskGroup, String taskName) {
		TaskScheduler taskScheduler = new TaskScheduler();
		taskScheduler.setTaskGroup(taskGroup);
		taskScheduler.setTaskName(taskName);
		return schedulerManager.runJob(taskScheduler);
	}

	// 暂停/恢复作业
	public boolean openCloseTask(String taskGroup, String taskName, String status) {
		TaskScheduler taskScheduler = new TaskScheduler();
		taskScheduler.setTaskGroup(taskGroup);
		taskScheduler.setTaskName(taskName);
		if ("start".equals(status)) {
			return schedulerManager.resumeJob(taskScheduler);
		} else if ("stop".equals(status)) {
			return schedulerManager.stopJob(taskScheduler);
		}
		return false;
	}

	public TaskScheduler queryById(Integer id) {
		return null;
	}
}
