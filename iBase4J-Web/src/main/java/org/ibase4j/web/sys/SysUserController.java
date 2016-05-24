/**
 * 
 */
package org.ibase4j.web.sys;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.ibase4j.core.support.BaseController;
import org.ibase4j.core.util.Request2ModelUtil;
import org.ibase4j.core.util.WebUtil;
import org.ibase4j.mybatis.generator.model.SysUser;
import org.ibase4j.mybatis.sys.model.SysMenuBean;
import org.ibase4j.service.sys.SysAuthorizeService;
import org.ibase4j.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

/**
 * 用户管理控制器
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:12:12
 */
@Controller
@RequestMapping("/user")
public class SysUserController extends BaseController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysAuthorizeService authorizeService;

	// 修改用户信息
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelMap update(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "account", required = false) String account) {
		SysUser sysUser = Request2ModelUtil.covert(SysUser.class, request);
		sysUserService.updateUserInfo(sysUser);
		return setSuccessModelMap(modelMap);
	}

	// 修改密码
	@ResponseBody
	@RequestMapping(value = "/update/password", method = RequestMethod.POST)
	public ModelMap updatePassword(ModelMap modelMap, @RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "password", required = false) String password) {
		sysUserService.updatePassword(id, password);
		return setSuccessModelMap(modelMap);
	}

	// 查询用户
	@ResponseBody
	@RequestMapping(value = "/read/list")
	public ModelMap get(HttpServletRequest request, ModelMap modelMap) {
		Map<String, Object> params = WebUtil.getParameterMap(request);
		PageInfo<?> list = sysUserService.queryBeans(params);
		return setSuccessModelMap(modelMap, list);
	}

	// 用户详细信息
	@ResponseBody
	@RequestMapping(value = "/read/detail")
	public ModelMap detail(ModelMap modelMap, @RequestParam(value = "id", required = false) Integer id) {
		SysUser sysUser = sysUserService.queryById(id);
		return setSuccessModelMap(modelMap, sysUser);
	}

	// 当前用户
	@ResponseBody
	@RequestMapping(value = "/read/current")
	public ModelMap current(ModelMap modelMap) {
		Integer id = getCurrUser();
		SysUser sysUser = sysUserService.queryById(id);
		List<SysMenuBean> menus = authorizeService.queryAuthorizeByUserId(id);
		modelMap.put("user", sysUser);
		modelMap.put("menus", menus);
		return setSuccessModelMap(modelMap);
	}
}
