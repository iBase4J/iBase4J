package org.ibase4j.web.sys;

import org.apache.shiro.SecurityUtils;
import org.ibase4j.core.config.Resources;
import org.ibase4j.core.support.HttpCode;
import org.ibase4j.core.support.shiro.LoginHelper;
import org.ibase4j.core.util.Request2ModelUtil;
import org.ibase4j.facade.sys.SysUserFacade;
import org.ibase4j.mybatis.generator.model.SysUser;
import org.ibase4j.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * 用户登录
 * 
 * @author ShenHuaJie
 */
@Controller
public class LoginController extends BaseController {
	@Reference
	private SysUserFacade sysUserFacade;

	// 登录
	@ResponseBody
	@RequestMapping(value = "/login")
	public ModelMap login(@RequestParam(value = "account", required = false) String account,
			@RequestParam(value = "password", required = false) String password) {
		Assert.notNull(account, Resources.getMessage("ACCOUNT_IS_NULL"));
		Assert.notNull(password, Resources.getMessage("PASSWORD_IS_NULL"));
		if (LoginHelper.login(account, sysUserFacade.encryptPassword(password))) {
			return setSuccessModelMap(modelMap);
		}
		throw new IllegalArgumentException(Resources.getMessage("LOGIN_FAIL"));
	}

	// 登出
	@ResponseBody
	@RequestMapping("/logout")
	public ModelMap logout() {
		SecurityUtils.getSubject().logout();
		return setSuccessModelMap(modelMap);
	}

	// 注册
	@ResponseBody
	@RequestMapping(value = "/regin")
	public ModelMap regin(@RequestParam(value = "account", required = false) String account,
			@RequestParam(value = "password", required = false) String password) {
		Assert.notNull(account, Resources.getMessage("ACCOUNT_IS_NULL"));
		Assert.notNull(password, Resources.getMessage("PASSWORD_IS_NULL"));
		SysUser sysUser = Request2ModelUtil.covert(SysUser.class, request);
		sysUser.setPassword(sysUserFacade.encryptPassword(password));
		sysUserFacade.update(sysUser);
		if (LoginHelper.login(account, password)) {
			return setSuccessModelMap(modelMap);
		}
		throw new IllegalArgumentException(Resources.getMessage("LOGIN_FAIL"));
	}

	// 没有登录
	@ResponseBody
	@RequestMapping("/unauthorized")
	public ModelMap unauthorized() {
		SecurityUtils.getSubject().logout();
		return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
	}

	// 没有权限
	@ResponseBody
	@RequestMapping("/forbidden")
	public ModelMap forbidden() {
		return setModelMap(modelMap, HttpCode.FORBIDDEN);
	}
}
