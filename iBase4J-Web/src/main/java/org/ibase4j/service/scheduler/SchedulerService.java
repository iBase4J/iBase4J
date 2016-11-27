package org.ibase4j.service.scheduler;

import java.util.List;
import java.util.Map;

import org.ibase4j.core.provider.scheduler.SchedulerProvider;
import org.ibase4j.core.support.Assert;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboReference;
import org.ibase4j.model.scheduler.TaskFireLog;
import org.ibase4j.model.scheduler.TaskGroup;
import org.ibase4j.model.scheduler.TaskScheduler;
import org.ibase4j.model.scheduler.ext.TaskScheduled;
import org.ibase4j.model.scheduler.ext.TaskSchedulerBean;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:16:20
 */
@Service
public class SchedulerService {
    @DubboReference // 依赖调度服务
    private SchedulerProvider schedulerProvider;

    public Page<TaskScheduled> getAllTaskDetail() {
        List<TaskScheduled> records = schedulerProvider.getAllTaskDetail();
        Page<TaskScheduled> pageInfo = new Page<TaskScheduled>(1, records.size());
        pageInfo.setRecords(records);
        pageInfo.setTotal(records.size());
        return pageInfo;
    }

    public boolean execTask(String taskGroup, String taskName) {
        Assert.notNull(taskGroup, "TASKGROUP");
        Assert.notNull(taskName, "TASKNAME");
        return schedulerProvider.execTask(taskGroup, taskName);
    }

    public boolean openTask(String taskGroup, String taskName) {
        Assert.notNull(taskGroup, "TASKGROUP");
        Assert.notNull(taskName, "TASKNAME");
        return schedulerProvider.openCloseTask(taskGroup, taskName, "start");
    }

    public boolean closeTask(String taskGroup, String taskName) {
        Assert.notNull(taskGroup, "TASKGROUP");
        Assert.notNull(taskName, "TASKNAME");
        return schedulerProvider.openCloseTask(taskGroup, taskName, "stop");
    }

    public boolean delTask(String taskGroup, String taskName) {
        Assert.notNull(taskGroup, "TASKGROUP");
        Assert.notNull(taskName, "TASKNAME");
        return schedulerProvider.delTask(taskGroup, taskName);
    }

    public Page<TaskGroup> queryGroup(Map<String, Object> params) {
        return schedulerProvider.queryGroup(params);
    }

    public Page<TaskSchedulerBean> queryScheduler(Map<String, Object> params) {
        return schedulerProvider.queryScheduler(params);
    }

    public Page<TaskFireLog> queryLog(Map<String, Object> params) {
        return schedulerProvider.queryLog(params);
    }

    /**
     * @param id
     * @return
     */
    public TaskGroup queryGroupById(String id) {
        Assert.notNull(id, "ID");
        return schedulerProvider.getGroupById(id);
    }

    /**
     * @param record
     */
    public void addGroup(TaskGroup record) {
        schedulerProvider.updateGroup(record);
    }

    /**
     * @param record
     */
    public void updateGroup(TaskGroup record) {
        Assert.isNotBlank(record.getId(), "ID");
        schedulerProvider.updateGroup(record);
    }

    /**
     * @param id
     * @return
     */
    public TaskScheduler querySchedulerById(String id) {
        Assert.isNotBlank(id, "ID");
        return schedulerProvider.getSchedulerById(id);
    }

    /**
     * @param record
     */
    public void addScheduler(TaskScheduler record) {
        schedulerProvider.updateScheduler(record);
    }

    /**
     * @param record
     */
    public void updateScheduler(TaskScheduler record) {
        Assert.notNull(record.getId(), "ID");
        schedulerProvider.updateScheduler(record);
    }
}
