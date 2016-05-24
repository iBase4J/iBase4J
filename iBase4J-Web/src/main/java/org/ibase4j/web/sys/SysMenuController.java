package org.ibase4j.web.sys;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.ibase4j.core.support.BaseController;
import org.ibase4j.core.util.WebUtil;
import org.ibase4j.service.sys.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

/**
 * 菜单管理
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:14:54
 */
@Controller
@RequestMapping("menu")
public class SysMenuController extends BaseController {
	@Autowired
	private SysMenuService sysMenuService;

	// 查询用户
	@ResponseBody
	@RequestMapping(value = "/read/list")
	public ModelMap get(HttpServletRequest request, ModelMap modelMap) {
		Map<String, Object> params = WebUtil.getParameterMap(request);
		PageInfo<?> list = sysMenuService.query(params);
		return setSuccessModelMap(modelMap, list);
	}
}
