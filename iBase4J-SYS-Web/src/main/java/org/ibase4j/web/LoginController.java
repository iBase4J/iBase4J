package org.ibase4j.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.ibase4j.model.SysUser;
import org.ibase4j.service.ISysSessionService;
import org.ibase4j.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import top.ibase4j.core.Constants;
import top.ibase4j.core.base.BaseController;
import top.ibase4j.core.config.Resources;
import top.ibase4j.core.exception.LoginException;
import top.ibase4j.core.support.Assert;
import top.ibase4j.core.support.HttpCode;
import top.ibase4j.core.support.login.LoginHelper;
import top.ibase4j.core.util.WebUtil;

/**
 * 用户登录
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:11:21
 */
@RestController
@Api(value = "登录接口", description = "登录接口")
public class LoginController extends BaseController {
	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private ISysSessionService sysSessionService;

	// 登录
	@ApiOperation(value = "用户登录")
	@PostMapping("/login")
	public Object login(@ApiParam(required = true, value = "登录帐号和密码") @RequestBody SysUser sysUser, ModelMap modelMap,
			HttpServletRequest request) {
		Assert.notNull(sysUser.getAccount(), "ACCOUNT");
		Assert.notNull(sysUser.getPassword(), "PASSWORD");
		String clientIp = (String) request.getSession().getAttribute(Constants.USER_IP);
		if (LoginHelper.login(sysUser.getAccount(), sysUserService.encryptPassword(sysUser.getPassword()), clientIp)) {
			request.setAttribute("msg", "[" + sysUser.getAccount() + "]登录成功.");
			return setSuccessModelMap(modelMap);
		}
		request.setAttribute("msg", "[" + sysUser.getAccount() + "]登录失败.");
		throw new LoginException(Resources.getMessage("LOGIN_FAIL"));
	}

	// 登出
	@ApiOperation(value = "用户登出")
	@PostMapping("/logout")
	public Object logout(ModelMap modelMap) {
		Long id = WebUtil.getCurrentUser();
		if (id != null) {
			sysSessionService.delete(id);
		}
		SecurityUtils.getSubject().logout();
		return setSuccessModelMap(modelMap);
	}

	// 注册
	@ApiOperation(value = "用户注册")
	@PostMapping("/regin")
	public Object regin(HttpServletRequest request, ModelMap modelMap, @RequestBody SysUser sysUser) {
		Assert.notNull(sysUser.getAccount(), "ACCOUNT");
		Assert.notNull(sysUser.getPassword(), "PASSWORD");
		sysUser.setPassword(sysUserService.encryptPassword(sysUser.getPassword()));
		sysUserService.update(sysUser);
		String clientIp = (String) request.getSession().getAttribute(Constants.USER_IP);
		if (LoginHelper.login(sysUser.getAccount(), sysUser.getPassword(), clientIp)) {
			return setSuccessModelMap(modelMap);
		}
		throw new IllegalArgumentException(Resources.getMessage("LOGIN_FAIL"));
	}

	// 没有登录
	@ApiOperation(value = "没有登录")
	@RequestMapping("/unauthorized")
	public Object unauthorized(ModelMap modelMap) {
		SecurityUtils.getSubject().logout();
		return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
	}

	// 没有权限
	@ApiOperation(value = "没有权限")
	@RequestMapping("/forbidden")
	public Object forbidden(ModelMap modelMap) {
		return setModelMap(modelMap, HttpCode.FORBIDDEN);
	}
}
