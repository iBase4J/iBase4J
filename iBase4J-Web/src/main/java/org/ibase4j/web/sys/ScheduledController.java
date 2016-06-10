/**
 * 
 */
package org.ibase4j.web.sys;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.ibase4j.core.support.BaseController;
import org.ibase4j.core.util.WebUtil;
import org.ibase4j.model.scheduler.TaskScheduled;
import org.ibase4j.service.sys.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

/**
 * 内存调度管理
 * 
 * @author ShenHuaJie
 * @version 2016年4月2日 下午4:20:10
 */
@RestController
@RequestMapping("/scheduled")
public class ScheduledController extends BaseController {
	@Autowired
	private SchedulerService schedulerService;

	@RequestMapping("/read/tasks")
	public Object list(ModelMap modelMap) {
		PageInfo<TaskScheduled> jobs = schedulerService.getAllJobDetail();
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

	// 执行记录
	@RequestMapping("/read/log")
	public Object getFireLog(HttpServletRequest request, ModelMap modelMap) {
		Map<String, Object> params = WebUtil.getParameterMap(request);
		PageInfo<?> list = schedulerService.queryLog(params);
		return setSuccessModelMap(modelMap, list);
	}
}
