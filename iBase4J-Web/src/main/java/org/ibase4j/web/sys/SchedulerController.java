package org.ibase4j.web.sys;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.core.base.BaseController;
import org.ibase4j.core.util.WebUtil;
import org.ibase4j.service.sys.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	@RequiresPermissions("task:group:read")
	public Object getGroup(HttpServletRequest request, ModelMap modelMap) {
		Map<String, Object> params = WebUtil.getParameterMap(request);
		PageInfo<?> list = schedulerService.queryGroup(params);
		return setSuccessModelMap(modelMap, list);
	}

	@ApiOperation(value = "任务列表")
	@RequestMapping("/read/schedulers")
	@RequiresPermissions("task:scheduler:read")
	public Object getScheduler(HttpServletRequest request, ModelMap modelMap) {
		Map<String, Object> params = WebUtil.getParameterMap(request);
		PageInfo<?> list = schedulerService.queryScheduler(params);
		return setSuccessModelMap(modelMap, list);
	}

}
