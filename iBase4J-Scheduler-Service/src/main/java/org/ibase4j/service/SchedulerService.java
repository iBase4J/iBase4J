package org.ibase4j.service;

import java.util.Date;

import org.ibase4j.dao.generator.TaskFireLogMapper;
import org.ibase4j.dao.generator.TaskGroupMapper;
import org.ibase4j.dao.generator.TaskSchedulerMapper;
import org.ibase4j.model.generator.TaskFireLog;
import org.ibase4j.model.generator.TaskGroup;
import org.ibase4j.model.generator.TaskScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	@CachePut("taskGroup")
	public TaskGroup updateGroup(TaskGroup record) {
		record.setEnable(1);
		if (record.getId() == null) {
			record.setCreateTime(new Date());
			taskGroupMapper.insert(record);
		} else {
			record.setUpdateTime(new Date());
			taskGroupMapper.updateByPrimaryKey(record);
		}
		return record;
	}

	@Transactional
	@CachePut("taskScheduler")
	public TaskScheduler updateScheduler(TaskScheduler record) {
		record.setEnable(1);
		if (record.getId() == null) {
			record.setCreateTime(new Date());
			taskSchedulerMapper.insert(record);
		} else {
			record.setUpdateTime(new Date());
			taskSchedulerMapper.updateByPrimaryKey(record);
		}
		return record;
	}

	@Transactional
	@CachePut("taskScheduler")
	public TaskScheduler deleteScheduler(Integer id) {
		TaskScheduler record = getSchedulerById(id);
		record.setEnable(0);
		record.setUpdateTime(new Date());
		taskSchedulerMapper.updateByPrimaryKey(record);
		return record;
	}

	@Transactional
	@CachePut("taskFireLog")
	public TaskFireLog updateLog(TaskFireLog record) {
		if (record.getId() == null) {
			logMapper.insert(record);
		} else {
			logMapper.updateByPrimaryKey(record);
		}
		return record;
	}
}
