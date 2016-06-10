package org.ibase4j.provider;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.ibase4j.core.support.dubbo.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.dao.scheduler.TaskSchedulerExpandMapper;
import org.ibase4j.model.generator.TaskFireLog;
import org.ibase4j.model.generator.TaskGroup;
import org.ibase4j.model.generator.TaskScheduler;
import org.ibase4j.model.scheduler.TaskScheduled;
import org.ibase4j.model.scheduler.TaskSchedulerBean;
import org.ibase4j.provider.scheduler.SchedulerProvider;
import org.ibase4j.scheduler.manager.SchedulerManager;
import org.ibase4j.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

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
	@Autowired
	private SchedulerService schedulerService;
	@Autowired
	private TaskSchedulerExpandMapper expandMapper;

	// 获取所有作业
	public List<TaskScheduled> getAllTaskDetail() {
		return schedulerManager.getAllJobDetail();
	}

	// 执行作业
	public boolean execTask(String taskGroup, String taskName) {
		TaskScheduled taskScheduler = new TaskScheduled();
		taskScheduler.setTaskGroup(taskGroup);
		taskScheduler.setTaskName(taskName);
		return schedulerManager.runJob(taskScheduler);
	}

	// 暂停/恢复作业
	public boolean openCloseTask(String taskGroup, String taskName, String status) {
		TaskScheduled taskScheduler = new TaskScheduled();
		taskScheduler.setTaskGroup(taskGroup);
		taskScheduler.setTaskName(taskName);
		if ("start".equals(status)) {
			return schedulerManager.resumeJob(taskScheduler);
		} else if ("stop".equals(status)) {
			return schedulerManager.stopJob(taskScheduler);
		}
		return false;
	}

	public TaskGroup getGroupById(Integer id) {
		return schedulerService.getGroupById(id);
	}

	public TaskScheduler getSchedulerById(Integer id) {
		return schedulerService.getSchedulerById(id);
	}

	public TaskFireLog getFireLogById(Integer id) {
		return schedulerService.getFireLogById(id);
	}

	public TaskScheduler queryById(Integer id) {
		return schedulerService.getSchedulerById(id);
	}

	public void updateGroup(TaskGroup record) {
		schedulerService.updateGroup(record);
	}

	public void updateScheduler(TaskScheduler record) {
		schedulerService.updateScheduler(record);
	}

	public PageInfo<TaskGroup> queryGroup(Map<String, Object> params) {
		Page<Integer> ids = expandMapper.queryGroup(params);
		Page<TaskGroup> page = new Page<TaskGroup>(ids.getPageNum(), ids.getPageSize());
		page.setTotal(ids.getTotal());
		if (ids != null) {
			page.clear();
			for (Integer id : ids) {
				page.add(schedulerService.getGroupById(id));
			}
		}
		return new PageInfo<TaskGroup>(page);
	}

	public PageInfo<TaskSchedulerBean> queryScheduler(Map<String, Object> params) {
		Page<Integer> ids = expandMapper.queryScheduler(params);
		Page<TaskSchedulerBean> page = new Page<TaskSchedulerBean>(ids.getPageNum(), ids.getPageSize());
		page.setTotal(ids.getTotal());
		if (ids != null) {
			page.clear();
			for (Integer id : ids) {
				TaskScheduler taskScheduler = schedulerService.getSchedulerById(id);
				TaskSchedulerBean bean = new TaskSchedulerBean();
				try {
					PropertyUtils.copyProperties(bean, taskScheduler);
				} catch (Exception e) {
				}
				TaskGroup taskGroup = schedulerService.getGroupById(bean.getGroupId());
				bean.setGroupName(taskGroup.getGroupName());
				bean.setTaskDesc(taskGroup.getGroupDesc() + ":" + bean.getTaskDesc());
				page.add(bean);
			}
		}
		return new PageInfo<TaskSchedulerBean>(page);
	}

	public PageInfo<TaskFireLog> queryLog(Map<String, Object> params) {
		Page<Integer> ids = expandMapper.queryLog(params);
		Page<TaskFireLog> page = new Page<TaskFireLog>(ids.getPageNum(), ids.getPageSize());
		page.setTotal(ids.getTotal());
		if (ids != null) {
			page.clear();
			for (Integer id : ids) {
				page.add(schedulerService.getFireLogById(id));
			}
		}
		return new PageInfo<TaskFireLog>(page);
	}
}
