package org.ibase4j.web.sys;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ibase4j.core.config.Resources;
import org.ibase4j.core.util.Request2ModelUtil;
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

@Controller
public class LoginController extends BaseController {
	@Autowired
	private SysUserService sysUserService;

	// 登录
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelMap login(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			@RequestParam(value = "account", required = false) String account,
			@RequestParam(value = "password", required = false) String password) {
		Assert.notNull(account, Resources.getMessage("ACCOUNT_IS_NULL"));
		Assert.notNull(password, Resources.getMessage("PASSWORD_IS_NULL"));
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("countSql", 0);
		params.put("usable", 1);
		params.put("account", account);
		params.put("password", SecurityUtil.encryptSHA(password));
		PageInfo<SysUser> pageInfo = sysUserService.query(params);
		if (pageInfo.getSize() == 1) {
			SysUser user = pageInfo.getList().get(0);
			WebUtil.saveCurrentUser(request, user.getId());
			return setSuccessModelMap(modelMap);
		}
		throw new IllegalArgumentException(Resources.getMessage("LOGIN_FAIL"));
	}

	// 登出
	@ResponseBody
	@RequestMapping("/logout")
	public ModelMap logout(ModelMap modelMap, HttpServletRequest request) {
		WebUtil.removeCurrentUser(request);
		return setSuccessModelMap(modelMap);
	}

	// 注册
	@ResponseBody
	@RequestMapping(value = "/regin", method = RequestMethod.POST)
	public ModelMap regin(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			@RequestParam(value = "account", required = false) String account,
			@RequestParam(value = "password", required = false) String password) {
		Assert.notNull(account, Resources.getMessage("ACCOUNT_IS_NULL"));
		Assert.notNull(password, Resources.getMessage("PASSWORD_IS_NULL"));
		SysUser sysUser = Request2ModelUtil.covert(SysUser.class, request);
		sysUser.setPassword(SecurityUtil.encryptSHA(password));
		sysUserService.update(sysUser);
		WebUtil.saveCurrentUser(request, sysUser.getId());
		return setSuccessModelMap(modelMap);
	}
}
