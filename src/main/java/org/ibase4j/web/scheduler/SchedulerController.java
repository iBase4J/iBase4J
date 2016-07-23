package org.ibase4j.web.scheduler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.core.base.BaseController;
import org.ibase4j.core.util.Request2ModelUtil;
import org.ibase4j.core.util.WebUtil;
import org.ibase4j.model.generator.TaskGroup;
import org.ibase4j.model.generator.TaskScheduler;
import org.ibase4j.service.sys.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 调度管理
 * 
 * @author ShenHuaJie
 * @version 2016年6月1日 下午3:14:24
 */
@RestController
@Api(value = "调度声明管理", description = "调度声明管理")
@RequestMapping(value = "/task", method = RequestMethod.POST)
public class SchedulerController extends BaseController {
	@Autowired
	private SchedulerService schedulerService;

	@RequestMapping("/read/groups")
	@ApiOperation(value = "任务组列表")
	@RequiresPermissions("task.group.read")
	public Object getGroup(HttpServletRequest request, ModelMap modelMap) {
		Map<String, Object> params = WebUtil.getParameterMap(request);
		PageInfo<?> list = schedulerService.queryGroup(params);
		return setSuccessModelMap(modelMap, list);
	}

	// 详细信息
	@ApiOperation(value = "任务组详情")
	@RequiresPermissions("task.group.read")
	@RequestMapping(value = "/read/group")
	public Object detail(ModelMap modelMap,
			@RequestParam(value = "id", required = false) Integer id) {
		TaskGroup record = schedulerService.getGroupById(id);
		return setSuccessModelMap(modelMap, record);
	}

	// 新增任务组
	@ApiOperation(value = "添加任务组")
	@RequiresPermissions("task.group.add")
	@RequestMapping(value = "/add/group", method = RequestMethod.POST)
	public Object add(HttpServletRequest request, ModelMap modelMap) {
		TaskGroup record = Request2ModelUtil.covert(TaskGroup.class, request);
		schedulerService.updateGroup(record);
		return setSuccessModelMap(modelMap);
	}

	// 修改任务组
	@ApiOperation(value = "修改任务组")
	@RequiresPermissions("task.group.update")
	@RequestMapping(value = "/update/group", method = RequestMethod.POST)
	public Object update(HttpServletRequest request, ModelMap modelMap) {
		TaskGroup record = Request2ModelUtil.covert(TaskGroup.class, request);
		schedulerService.updateGroup(record);
		return setSuccessModelMap(modelMap);
	}

	@ApiOperation(value = "任务列表")
	@RequestMapping("/read/schedulers")
	@RequiresPermissions("task.scheduler.read")
	public Object getScheduler(HttpServletRequest request, ModelMap modelMap) {
		Map<String, Object> params = WebUtil.getParameterMap(request);
		PageInfo<?> list = schedulerService.queryScheduler(params);
		return setSuccessModelMap(modelMap, list);
	}

	// 详细信息
	@ApiOperation(value = "任务详情")
	@RequiresPermissions("task.scheduler.read")
	@RequestMapping(value = "/read/scheduler")
	public Object detailScheduler(ModelMap modelMap,
			@RequestParam(value = "id", required = false) Integer id) {
		TaskScheduler record = schedulerService.getSchedulerById(id);
		return setSuccessModelMap(modelMap, record);
	}

	// 新增任务
	@ApiOperation(value = "添加任务")
	@RequiresPermissions("task.scheduler.add")
	@RequestMapping(value = "/add/scheduler", method = RequestMethod.POST)
	public Object addScheduler(HttpServletRequest request, ModelMap modelMap) {
		TaskScheduler record = Request2ModelUtil.covert(TaskScheduler.class, request);
		schedulerService.updateScheduler(record);
		return setSuccessModelMap(modelMap);
	}

	// 修改任务
	@ApiOperation(value = "修改任务")
	@RequiresPermissions("task.scheduler.update")
	@RequestMapping(value = "/update/scheduler", method = RequestMethod.POST)
	public Object updateScheduler(HttpServletRequest request, ModelMap modelMap) {
		TaskScheduler record = Request2ModelUtil.covert(TaskScheduler.class, request);
		schedulerService.updateScheduler(record);
		return setSuccessModelMap(modelMap);
	}
}
