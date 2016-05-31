package org.ibase4j.web.sys;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.ibase4j.core.support.BaseController;
import org.ibase4j.core.util.WebUtil;
import org.ibase4j.service.sys.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

/**
 * @author ShenHuaJie
 * @version 2016年5月26日 下午2:57:12
 */
@RestController
@RequestMapping("permission")
public class SysPermissionController extends BaseController {
	@Autowired
	private SysPermissionService sysPermissionService;

	@RequestMapping(value = "/read/list")
	public Object get(HttpServletRequest request, ModelMap modelMap) {
		Map<String, Object> params = WebUtil.getParameterMap(request);
		PageInfo<?> list = sysPermissionService.query(params);
		return setSuccessModelMap(modelMap, list);
	}
}
