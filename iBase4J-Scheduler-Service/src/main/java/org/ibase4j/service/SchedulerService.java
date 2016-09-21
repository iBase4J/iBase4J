package org.ibase4j.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.dao.scheduler.TaskFireLogMapper;
import org.ibase4j.dao.scheduler.TaskGroupMapper;
import org.ibase4j.dao.scheduler.TaskSchedulerMapper;
import org.ibase4j.model.scheduler.TaskFireLog;
import org.ibase4j.model.scheduler.TaskGroup;
import org.ibase4j.model.scheduler.TaskScheduler;
import org.ibase4j.model.scheduler.ext.TaskSchedulerBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * @author ShenHuaJie
 * @version 2016年7月1日 上午11:34:59
 */
@Service
public class SchedulerService extends BaseProviderImpl<TaskGroup> {
    @Autowired
    private TaskGroupMapper taskGroupMapper;
    @Autowired
    private TaskSchedulerMapper taskSchedulerMapper;
    @Autowired
    private TaskFireLogMapper logMapper;

    @Cacheable("taskGroup")
    public TaskGroup getGroupById(String id) {
        return taskGroupMapper.selectById(id);
    }

    @Cacheable("taskScheduler")
    public TaskScheduler getSchedulerById(String id) {
        return taskSchedulerMapper.selectById(id);
    }

    @Cacheable("taskFireLog")
    public TaskFireLog getFireLogById(String id) {
        return logMapper.selectById(id);
    }

    @Transactional
    @CachePut("taskGroup")
    public TaskGroup updateGroup(TaskGroup record) {
        record.setEnable(true);
        if (StringUtils.isBlank(record.getId())) {
            record.setId(createId("TaskGroup"));
            record.setCreateTime(new Date());
            taskGroupMapper.insert(record);
        } else {
            record.setUpdateTime(new Date());
            taskGroupMapper.updateById(record);
        }
        return record;
    }

    @Transactional
    @CachePut("taskScheduler")
    public TaskScheduler updateScheduler(TaskScheduler record) {
        record.setEnable(true);
        if (StringUtils.isBlank(record.getId())) {
            record.setId(createId("TaskScheduler"));
            record.setCreateTime(new Date());
            taskSchedulerMapper.insert(record);
        } else {
            record.setUpdateTime(new Date());
            taskSchedulerMapper.updateById(record);
        }
        return record;
    }

    @Transactional
    @CachePut("taskFireLog")
    public TaskFireLog updateLog(TaskFireLog record) {
        if (StringUtils.isBlank(record.getId())) {
            record.setId(createId("TaskFireLog"));
            logMapper.insert(record);
        } else {
            logMapper.updateById(record);
        }
        return record;
    }

    public Page<TaskGroup> queryGroup(Map<String, Object> params) {
        Page<String> ids = getPage(params);
        ids.setRecords(taskGroupMapper.selectIdByMap(ids, params));

        Page<TaskGroup> page = new Page<TaskGroup>(ids.getCurrent(), ids.getSize());
        page.setTotal(ids.getTotal());
        if (ids != null) {
            List<TaskGroup> records = InstanceUtil.newArrayList();
            for (String id : ids.getRecords()) {
                records.add(InstanceUtil.getBean(getClass()).getGroupById(id));
            }
            page.setRecords(records);
        }
        return page;
    }

    public Page<TaskSchedulerBean> queryScheduler(Map<String, Object> params) {
        Page<String> ids = getPage(params);
        ids.setRecords(taskSchedulerMapper.selectIdByMap(ids, params));
        Page<TaskSchedulerBean> page = new Page<TaskSchedulerBean>(ids.getCurrent(), ids.getSize());
        page.setTotal(ids.getTotal());
        if (ids != null) {
            List<TaskSchedulerBean> records = InstanceUtil.newArrayList();
            for (String id : ids.getRecords()) {
                TaskScheduler taskScheduler = InstanceUtil.getBean(getClass()).getSchedulerById(id);
                TaskSchedulerBean bean = InstanceUtil.to(taskScheduler, TaskSchedulerBean.class);
                TaskGroup taskGroup = InstanceUtil.getBean(getClass()).getGroupById(bean.getGroupId());
                bean.setGroupName(taskGroup.getGroupName());
                bean.setTaskDesc(taskGroup.getGroupDesc() + ":" + bean.getTaskDesc());
                records.add(bean);
            }
            page.setRecords(records);
        }
        return page;
    }

    public Page<TaskFireLog> queryLog(Map<String, Object> params) {
        Page<String> ids = getPage(params);
        ids.setRecords(logMapper.selectIdByMap(ids, params));
        Page<TaskFireLog> page = new Page<TaskFireLog>(ids.getCurrent(), ids.getSize());
        page.setTotal(ids.getTotal());
        if (ids != null) {
            List<TaskFireLog> records = InstanceUtil.newArrayList();
            for (String id : ids.getRecords()) {
                records.add(InstanceUtil.getBean(getClass()).getFireLogById(id));
            }
            page.setRecords(records);
        }
        return page;
    }
}
