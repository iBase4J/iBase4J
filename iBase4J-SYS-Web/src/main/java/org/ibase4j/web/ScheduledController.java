/**
 * 
 */
package org.ibase4j.web;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.provider.ISysProvider;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.ibase4j.core.base.provider.BaseController;
import top.ibase4j.core.base.provider.Parameter;
import top.ibase4j.core.support.Assert;
import top.ibase4j.core.support.scheduler.TaskScheduled;
import top.ibase4j.core.support.scheduler.TaskScheduled.TaskType;

/**
 * 内存调度管理
 * 
 * @author ShenHuaJie
 * @version 2016年4月2日 下午4:20:10
 */
@RestController
@Api(value = "调度管理", description = "调度管理")
@RequestMapping(value = "/scheduled")
public class ScheduledController extends BaseController<ISysProvider> {
    public String getService() {
        return "scheduledService";
    }

    @PostMapping
    @ApiOperation(value = "新增任务")
    @RequiresPermissions("sys.task.scheduled.update")
    public Object updateTask(@RequestBody TaskScheduled scheduled, ModelMap modelMap) {
        Assert.notNull(scheduled.getJobType(), "JOBTYPE");
        Assert.notNull(scheduled.getTaskType(), "TASKTYPE");
        Assert.notNull(scheduled.getTargetObject(), "TARGETOBJECT");
        Assert.notNull(scheduled.getTargetMethod(), "TARGETMETHOD");
        Assert.notNull(scheduled.getTaskCron(), "TASKCRON");
        Assert.notNull(scheduled.getTaskDesc(), "TASKDESC");
        if (TaskType.dubbo.equals(scheduled.getTaskType())) {
            Assert.notNull(scheduled.getTargetSystem(), "TARGETSYSTEM");
        }
        Parameter parameter = new Parameter(getService(), "updateTask", scheduled);
        provider.execute(parameter);
        return setSuccessModelMap(modelMap);
    }

    @DeleteMapping
    @ApiOperation(value = "删除任务")
    @RequiresPermissions("sys.task.scheduled.delete")
    public Object delete(@RequestBody TaskScheduled scheduled, ModelMap modelMap) {
        Assert.notNull(scheduled.getTaskGroup(), "TASKGROUP");
        Assert.notNull(scheduled.getTaskName(), "TASKNAME");
        Parameter parameter = new Parameter(getService(), "delTask", scheduled);
        provider.execute(parameter);
        return setSuccessModelMap(modelMap);
    }

    @PostMapping("/run")
    @ApiOperation(value = "立即执行任务")
    @RequiresPermissions("sys.task.scheduled.run")
    public Object exec(@RequestBody TaskScheduled scheduled, ModelMap modelMap) {
        Assert.notNull(scheduled.getTaskGroup(), "TASKGROUP");
        Assert.notNull(scheduled.getTaskName(), "TASKNAME");
        Parameter parameter = new Parameter(getService(), "execTask", scheduled);
        provider.execute(parameter);
        return setSuccessModelMap(modelMap);
    }

    @PostMapping("/open")
    @ApiOperation(value = "启动任务")
    @RequiresPermissions("sys.task.scheduled.open")
    public Object open(@RequestBody TaskScheduled scheduled, ModelMap modelMap) {
        Assert.notNull(scheduled.getTaskGroup(), "TASKGROUP");
        Assert.notNull(scheduled.getTaskName(), "TASKNAME");
        Parameter parameter = new Parameter(getService(), "openTask", scheduled);
        provider.execute(parameter);
        return setSuccessModelMap(modelMap);
    }

    @PostMapping("/close")
    @ApiOperation(value = "暂停任务")
    @RequiresPermissions("sys.task.scheduled.close")
    public Object close(@RequestBody TaskScheduled scheduled, ModelMap modelMap) {
        Assert.notNull(scheduled.getTaskGroup(), "TASKGROUP");
        Assert.notNull(scheduled.getTaskName(), "TASKNAME");
        Parameter parameter = new Parameter(getService(), "closeTask", scheduled);
        provider.execute(parameter);
        return setSuccessModelMap(modelMap);
    }

    @PutMapping("/read/tasks")
    @ApiOperation(value = "任务列表")
    @RequiresPermissions("sys.task.scheduled.read")
    public Object list(ModelMap modelMap) {
        Parameter parameter = new Parameter(getService(), "getAllTaskDetail");
        List<?> records = provider.execute(parameter).getResultList();
        modelMap.put("recordsTotal", records.size());
        modelMap.put("total", records.size());
        modelMap.put("current", 1);
        modelMap.put("size", records.size());
        return setSuccessModelMap(modelMap, records);
    }

    @PutMapping("/read/log")
    @ApiOperation(value = "任务执行记录")
    @RequiresPermissions("sys.task.log.read")
    public Object getFireLog(ModelMap modelMap, @RequestBody Map<String, Object> log) {
        Parameter parameter = new Parameter(getService(), "queryLog", log);
        Page<?> list = provider.execute(parameter).getResultPage();
        return setSuccessModelMap(modelMap, list);
    }
}
