/**
 * 
 */
package org.ibase4j.web.sys;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.ibase4j.core.config.Resource;
import org.ibase4j.core.util.Request2ModelUtils;
import org.ibase4j.core.util.SecurityUtil;
import org.ibase4j.core.util.WebUtil;
import org.ibase4j.mybatis.generator.model.SysUser;
import org.ibase4j.service.sys.SysUserService;
import org.ibase4j.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

/**
 * @author ShenHuaJie
 */
@Controller
@RequestMapping("/user")
public class SysUserController extends BaseController {
	@Autowired
	private SysUserService sysUserService;

	// 修改用户信息
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelMap update(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "account", required = false) String account) {
		Assert.notNull(id, Resource.RESOURCE.getString("USER_ID_IS_NULL"));
		Assert.notNull(account, Resource.RESOURCE.getString("ACCOUNT_IS_NULL"));
		SysUser sysUser = Request2ModelUtils.covert(SysUser.class, request);
		SysUser user = sysUserService.queryById(sysUser.getId());
		Assert.notNull(user, String.format(Resource.RESOURCE.getString("USER_IS_NULL"), id));
		sysUser.setPassword(user.getPassword());
		sysUserService.update(sysUser);
		return setSuccessModelMap(modelMap);
	}

	// 修改密码
	@ResponseBody
	@RequestMapping(value = "/update/password", method = RequestMethod.POST)
	public ModelMap updatePassword(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "password", required = false) String password) {
		Assert.notNull(id, Resource.RESOURCE.getString("USER_ID_IS_NULL"));
		Assert.notNull(password, Resource.RESOURCE.getString("PASSWORD_IS_NULL"));
		SysUser sysUser = sysUserService.queryById(id);
		Assert.notNull(sysUser, String.format(Resource.RESOURCE.getString("USER_IS_NULL"), id));
		sysUser.setPassword(SecurityUtil.encryptSHA(password));
		sysUserService.update(sysUser);
		return setSuccessModelMap(modelMap);
	}

	// 查询用户
	@ResponseBody
	@RequestMapping(value = "/read/list")
	public ModelMap get(ModelMap modelMap, HttpServletRequest request) {
		Map<String, Object> params = WebUtil.getParameterMap(request);
		PageInfo<?> list = sysUserService.query(params);
		return setSuccessModelMap(modelMap, list);
	}
}
