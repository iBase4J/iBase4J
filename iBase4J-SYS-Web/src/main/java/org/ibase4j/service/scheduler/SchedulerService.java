package org.ibase4j.service.scheduler;

import java.util.List;
import java.util.Map;

import org.ibase4j.core.support.Assert;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboReference;
import org.ibase4j.model.scheduler.TaskFireLog;
import org.ibase4j.model.scheduler.TaskScheduled;
import org.ibase4j.provider.scheduler.SchedulerProvider;
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

    // 修改执行计划
    public void updateTask(TaskScheduled scheduled) {
        Assert.notNull(scheduled.getJobType(), "JOBTYPE");
        Assert.notNull(scheduled.getTaskType(), "TASKTYPE");
        Assert.notNull(scheduled.getTargetObject(), "TARGETOBJECT");
        Assert.notNull(scheduled.getTargetMethod(), "TARGETMETHOD");
        Assert.notNull(scheduled.getTaskCron(), "TASKCRON");
        Assert.notNull(scheduled.getTaskDesc(), "TASKDESC");
        schedulerProvider.updateTask(scheduled);
    }

    public void execTask(String taskGroup, String taskName) {
        Assert.notNull(taskGroup, "TASKGROUP");
        Assert.notNull(taskName, "TASKNAME");
        schedulerProvider.execTask(taskGroup, taskName);
    }

    public void openTask(String taskGroup, String taskName) {
        Assert.notNull(taskGroup, "TASKGROUP");
        Assert.notNull(taskName, "TASKNAME");
        schedulerProvider.openCloseTask(taskGroup, taskName, "start");
    }

    public void closeTask(String taskGroup, String taskName) {
        Assert.notNull(taskGroup, "TASKGROUP");
        Assert.notNull(taskName, "TASKNAME");
        schedulerProvider.openCloseTask(taskGroup, taskName, "stop");
    }

    public void delTask(String taskGroup, String taskName) {
        Assert.notNull(taskGroup, "TASKGROUP");
        Assert.notNull(taskName, "TASKNAME");
        schedulerProvider.delTask(taskGroup, taskName);
    }

    public Page<TaskFireLog> queryLog(Map<String, Object> params) {
        return schedulerProvider.queryLog(params);
    }
}
