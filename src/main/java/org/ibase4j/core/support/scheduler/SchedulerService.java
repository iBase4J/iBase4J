package org.ibase4j.core.support.scheduler;

import java.util.List;
import java.util.Map;

import org.ibase4j.core.base.BaseService;
import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.mapper.scheduler.TaskFireLogMapper;
import org.ibase4j.model.scheduler.TaskFireLog;
import org.ibase4j.model.scheduler.TaskScheduled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * @author ShenHuaJie
 * @version 2016年7月1日 上午11:34:59
 */
public class SchedulerService {
	@Autowired
	private TaskFireLogMapper logMapper;
	@Autowired
	private SchedulerManager schedulerManager;

	// 获取所有作业
	public List<TaskScheduled> getAllTaskDetail() {
		return schedulerManager.getAllJobDetail();
	}

	// 执行作业
	public void execTask(TaskScheduled taskScheduler) {
		schedulerManager.runJob(taskScheduler);
	}

	// 恢复作业
	public void openTask(TaskScheduled taskScheduled) {
		schedulerManager.resumeJob(taskScheduled);
	}

	// 暂停作业
	public void closeTask(TaskScheduled taskScheduled) {
		schedulerManager.stopJob(taskScheduled);
	}

	// 删除作业
	public void delTask(TaskScheduled taskScheduled) {
		schedulerManager.delJob(taskScheduled);
	}

	// 修改任务
	public void updateTask(TaskScheduled taskScheduled) {
		schedulerManager.updateTask(taskScheduled);
	}

	@Cacheable("taskFireLog")
	public TaskFireLog getFireLogById(Long id) {
		return logMapper.selectById(id);
	}

	@Transactional
	@CachePut("taskFireLog")
	public TaskFireLog updateLog(TaskFireLog record) {
		if (record.getId() == null) {
			logMapper.insert(record);
		} else {
			logMapper.updateById(record);
		}
		return record;
	}

	public Page<TaskFireLog> queryLog(Map<String, Object> params) {
		Page<Long> ids = BaseService.getPage(params);
		ids.setRecords(logMapper.selectIdByMap(ids, params));
		Page<TaskFireLog> page = new Page<TaskFireLog>(ids.getCurrent(), ids.getSize());
		page.setTotal(ids.getTotal());
		if (ids != null) {
			List<TaskFireLog> records = InstanceUtil.newArrayList();
			for (Long id : ids.getRecords()) {
				records.add(InstanceUtil.newInstance(getClass()).getFireLogById(id));
			}
			page.setRecords(records);
		}
		return page;
	}
}
