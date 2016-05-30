/**
 * 
 */
package org.ibase4j.core.support.scheduled.web;

import java.util.List;

import org.ibase4j.core.support.BaseController;
import org.ibase4j.core.support.scheduled.TaskScheduler;
import org.ibase4j.core.support.scheduled.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 定时任务管理
 * 
 * @author ShenHuaJie
 * @version 2016年4月2日 下午4:20:10
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController extends BaseController {
	@Autowired
	private ScheduleService scheduleService;

	@RequestMapping("/read/tasks")
	public Object list(ModelMap modelMap) {
		List<TaskScheduler> jobs = scheduleService.getAllJobDetail();
		return setSuccessModelMap(modelMap, jobs);
	}

	// 执行
	@RequestMapping("/run/task")
	public Object exec(ModelMap modelMap, @RequestParam(value = "taskGroup", required = false) String taskGroup,
			@RequestParam(value = "taskName", required = false) String taskName) {
		scheduleService.execTask(taskGroup, taskName);
		return setSuccessModelMap(modelMap);
	}

	// 启动
	@RequestMapping("/open/task")
	public Object open(ModelMap modelMap, @RequestParam(value = "taskGroup", required = false) String taskGroup,
			@RequestParam(value = "taskName", required = false) String taskName) {
		scheduleService.openTask(taskGroup, taskName);
		return setSuccessModelMap(modelMap);
	}

	// 暂停
	@RequestMapping("/close/task")
	public Object close(ModelMap modelMap, @RequestParam(value = "taskGroup", required = false) String taskGroup,
			@RequestParam(value = "taskName", required = false) String taskName) {
		scheduleService.closeTask(taskGroup, taskName);
		return setSuccessModelMap(modelMap);
	}
}
