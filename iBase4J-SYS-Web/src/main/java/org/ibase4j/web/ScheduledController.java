/**
 * 
 */
package org.ibase4j.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.core.base.BaseController;
import org.ibase4j.core.support.scheduler.TaskScheduled;
import org.ibase4j.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * 内存调度管理
 * 
 * @author ShenHuaJie
 * @version 2016年4月2日 下午4:20:10
 */
@RestController
@Api(value = "调度管理", description = "调度管理")
@RequestMapping(value = "/scheduled")
public class ScheduledController extends BaseController {
	@Autowired
	private SchedulerService schedulerService;

	@PostMapping
	@ApiOperation(value = "新增任务")
	@RequiresPermissions("sys.task.scheduled.update")
	public Object updateTask(@RequestBody TaskScheduled scheduled, ModelMap modelMap) {
		schedulerService.updateTask(scheduled);
		return setSuccessModelMap(modelMap);
	}

	@DeleteMapping
	@ApiOperation(value = "删除任务")
	@RequiresPermissions("sys.task.scheduled.close")
	public Object delete(@RequestBody TaskScheduled scheduled, ModelMap modelMap) {
		schedulerService.delTask(scheduled.getTaskGroup(), scheduled.getTaskName());
		return setSuccessModelMap(modelMap);
	}

	@PostMapping("/run")
	@ApiOperation(value = "立即执行任务")
	@RequiresPermissions("sys.task.scheduled.run")
	public Object exec(@RequestBody TaskScheduled scheduled, ModelMap modelMap) {
		schedulerService.execTask(scheduled.getTaskGroup(), scheduled.getTaskName());
		return setSuccessModelMap(modelMap);
	}

	@PostMapping("/open")
	@ApiOperation(value = "启动任务")
	@RequiresPermissions("sys.task.scheduled.open")
	public Object open(@RequestBody TaskScheduled scheduled, ModelMap modelMap) {
		schedulerService.openTask(scheduled.getTaskGroup(), scheduled.getTaskName());
		return setSuccessModelMap(modelMap);
	}

	@PostMapping("/close")
	@ApiOperation(value = "暂停任务")
	@RequiresPermissions("sys.task.scheduled.close")
	public Object close(@RequestBody TaskScheduled scheduled, ModelMap modelMap) {
		schedulerService.closeTask(scheduled.getTaskGroup(), scheduled.getTaskName());
		return setSuccessModelMap(modelMap);
	}

	@PutMapping("/read/tasks")
	@ApiOperation(value = "任务列表")
	@RequiresPermissions("sys.task.scheduled.read")
	public Object list(ModelMap modelMap) {
		Page<TaskScheduled> jobs = schedulerService.getAllTaskDetail();
		return setSuccessModelMap(modelMap, jobs);
	}

	@PutMapping("/read/log")
	@ApiOperation(value = "任务执行记录")
	@RequiresPermissions("sys.task.log.read")
	public Object getFireLog(HttpServletRequest request, ModelMap modelMap, @RequestBody Map<String, Object> log) {
		Page<?> list = schedulerService.queryLog(log);
		return setSuccessModelMap(modelMap, list);
	}
}