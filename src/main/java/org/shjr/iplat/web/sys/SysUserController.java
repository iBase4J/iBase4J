/**
 * 
 */
package org.shjr.iplat.web.sys;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.shjr.iplat.core.Constants;
import org.shjr.iplat.core.util.Request2ModelUtils;
import org.shjr.iplat.core.util.SecurityUtil;
import org.shjr.iplat.core.util.WebUtil;
import org.shjr.iplat.mybatis.generator.model.SysUser;
import org.shjr.iplat.service.sys.SysUserService;
import org.shjr.iplat.web.BaseController;
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
			@RequestParam(value = "account", required = false) String account,
			@RequestParam(value = "password", required = false) String password) {
		Assert.notNull(account, Constants.ACCOUNT_IS_NULL);
		Assert.notNull(password, Constants.PASSWORD_IS_NULL);
		SysUser sysUser = Request2ModelUtils.covert(SysUser.class, request);
		if (sysUser.getId() == null) {
			sysUser.setPassword(SecurityUtil.encryptSHA(password));
			sysUserService.add(sysUser);
		} else {
			sysUserService.update(sysUser);
		}
		return setSuccessModelMap(modelMap);
	}

	// 修改密码
	@ResponseBody
	@RequestMapping(value = "/update/password", method = RequestMethod.POST)
	public ModelMap updatePassword(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "password", required = false) String password) {
		Assert.notNull(id, Constants.USER_ID_IS_NULL);
		Assert.notNull(password, Constants.PASSWORD_IS_NULL);
		SysUser sysUser = sysUserService.queryById(id);
		Assert.notNull(sysUser, String.format(Constants.USER_IS_NULL, id));
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
