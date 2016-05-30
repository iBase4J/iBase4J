/**
 * 
 */
package org.ibase4j.web.sys;

import java.util.List;

import org.ibase4j.core.support.BaseController;
import org.ibase4j.core.support.scheduler.TaskScheduler;
import org.ibase4j.service.sys.SchedulerService;
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
public class SchedulerController extends BaseController {
	@Autowired
	private SchedulerService schedulerService;

	@RequestMapping("/read/tasks")
	public Object list(ModelMap modelMap) {
		List<TaskScheduler> jobs = schedulerService.getAllJobDetail();
		return setSuccessModelMap(modelMap, jobs);
	}

	// 执行
	@RequestMapping("/run/task")
	public Object exec(ModelMap modelMap, @RequestParam(value = "taskGroup", required = false) String taskGroup,
			@RequestParam(value = "taskName", required = false) String taskName) {
		schedulerService.execTask(taskGroup, taskName);
		return setSuccessModelMap(modelMap);
	}

	// 启动
	@RequestMapping("/open/task")
	public Object open(ModelMap modelMap, @RequestParam(value = "taskGroup", required = false) String taskGroup,
			@RequestParam(value = "taskName", required = false) String taskName) {
		schedulerService.openTask(taskGroup, taskName);
		return setSuccessModelMap(modelMap);
	}

	// 暂停
	@RequestMapping("/close/task")
	public Object close(ModelMap modelMap, @RequestParam(value = "taskGroup", required = false) String taskGroup,
			@RequestParam(value = "taskName", required = false) String taskName) {
		schedulerService.closeTask(taskGroup, taskName);
		return setSuccessModelMap(modelMap);
	}
}
