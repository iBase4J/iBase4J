package org.ibase4j.web.sys;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.ibase4j.core.base.BaseController;
import org.ibase4j.core.util.WebUtil;
import org.ibase4j.service.sys.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

/**
 * 调度管理
 * 
 * @author ShenHuaJie
 * @version 2016年6月1日 下午3:14:24
 */
@RestController
@RequestMapping("/task")
public class SchedulerController extends BaseController {
	@Autowired
	private SchedulerService schedulerService;

	@RequestMapping("/read/groups")
	public Object getGroup(HttpServletRequest request, ModelMap modelMap) {
		Map<String, Object> params = WebUtil.getParameterMap(request);
		PageInfo<?> list = schedulerService.queryGroup(params);
		return setSuccessModelMap(modelMap, list);
	}

	@RequestMapping("/read/schedulers")
	public Object getScheduler(HttpServletRequest request, ModelMap modelMap) {
		Map<String, Object> params = WebUtil.getParameterMap(request);
		PageInfo<?> list = schedulerService.queryScheduler(params);
		return setSuccessModelMap(modelMap, list);
	}

}
