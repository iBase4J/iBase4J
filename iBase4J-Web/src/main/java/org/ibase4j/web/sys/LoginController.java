package org.ibase4j.web.sys;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.ibase4j.core.base.BaseController;
import org.ibase4j.core.config.Resources;
import org.ibase4j.core.support.HttpCode;
import org.ibase4j.core.support.exception.LoginException;
import org.ibase4j.core.support.login.LoginHelper;
import org.ibase4j.core.util.Request2ModelUtil;
import org.ibase4j.model.generator.SysUser;
import org.ibase4j.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户登录
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:11:21
 */
@RestController
public class LoginController extends BaseController {
	@Autowired
	private SysUserService sysUserService;

	// 登录
	@RequestMapping(value = "/login")
	public Object login(ModelMap modelMap, @RequestParam(value = "account", required = false) String account,
			@RequestParam(value = "password", required = false) String password) {
		Assert.notNull(account, Resources.getMessage("ACCOUNT_IS_NULL"));
		Assert.notNull(password, Resources.getMessage("PASSWORD_IS_NULL"));
		if (LoginHelper.login(account, sysUserService.encryptPassword(password))) {
			return setSuccessModelMap(modelMap);
		}
		throw new LoginException(Resources.getMessage("LOGIN_FAIL"));
	}

	// 登出
	@RequestMapping("/logout")
	public Object logout(ModelMap modelMap) {
		SecurityUtils.getSubject().logout();
		return setSuccessModelMap(modelMap);
	}

	// 注册
	@RequestMapping(value = "/regin")
	public Object regin(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "account", required = false) String account,
			@RequestParam(value = "password", required = false) String password) {
		SysUser sysUser = Request2ModelUtil.covert(SysUser.class, request);
		Assert.notNull(sysUser.getAccount(), Resources.getMessage("ACCOUNT_IS_NULL"));
		Assert.notNull(sysUser.getPassword(), Resources.getMessage("PASSWORD_IS_NULL"));
		sysUser.setPassword(sysUserService.encryptPassword(sysUser.getPassword()));
		sysUserService.add(sysUser);
		if (LoginHelper.login(account, password)) {
			return setSuccessModelMap(modelMap);
		}
		throw new IllegalArgumentException(Resources.getMessage("LOGIN_FAIL"));
	}

	// 没有登录
	@RequestMapping("/unauthorized")
	public Object unauthorized(ModelMap modelMap) {
		SecurityUtils.getSubject().logout();
		return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
	}

	// 没有权限
	@RequestMapping("/forbidden")
	public Object forbidden(ModelMap modelMap) {
		return setModelMap(modelMap, HttpCode.FORBIDDEN);
	}
}
