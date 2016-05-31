package org.ibase4j.service;

import org.ibase4j.mybatis.generator.dao.TaskFireLogMapper;
import org.ibase4j.mybatis.generator.dao.TaskGroupMapper;
import org.ibase4j.mybatis.generator.dao.TaskSchedulerMapper;
import org.ibase4j.mybatis.generator.model.TaskFireLog;
import org.ibase4j.mybatis.generator.model.TaskGroup;
import org.ibase4j.mybatis.generator.model.TaskScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author ShenHuaJie
 * @version 2016年5月27日 下午4:33:07
 */
@Service
public class SchedulerService {
	@Autowired
	private TaskGroupMapper taskGroupMapper;
	@Autowired
	private TaskSchedulerMapper taskSchedulerMapper;
	@Autowired
	private TaskFireLogMapper logMapper;

	@Cacheable("taskGroup")
	public TaskGroup getGroupById(Integer id) {
		return taskGroupMapper.selectByPrimaryKey(id);
	}

	@Cacheable("taskScheduler")
	public TaskScheduler getSchedulerById(Integer id) {
		return taskSchedulerMapper.selectByPrimaryKey(id);
	}

	@Cacheable("taskFireLog")
	public TaskFireLog getFireLogById(Integer id) {
		return logMapper.selectByPrimaryKey(id);
	}

	@CachePut("taskGroup")
	public void updateGroup(TaskGroup record) {
		if (record.getId() == null) {
			taskGroupMapper.insert(record);
		} else {
			taskGroupMapper.updateByPrimaryKey(record);
		}
	}

	@CachePut("taskScheduler")
	public void updateScheduler(TaskScheduler record) {
		if (record.getId() == null) {
			taskSchedulerMapper.insert(record);
		} else {
			taskSchedulerMapper.updateByPrimaryKey(record);
		}
	}

	@CachePut("taskFireLog")
	public void updateLog(TaskFireLog record) {
		if (record.getId() == null) {
			logMapper.insert(record);
		} else {
			logMapper.updateByPrimaryKey(record);
		}
	}
}
