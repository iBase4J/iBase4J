/**
 * 
 */
package org.shjr.iplat.web.sys;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.shjr.iplat.core.exception.ParameterException;
import org.shjr.iplat.core.util.Request2ModelUtils;
import org.shjr.iplat.core.util.SecurityUtil;
import org.shjr.iplat.core.util.WebUtil;
import org.shjr.iplat.mybatis.generator.model.SysUser;
import org.shjr.iplat.service.sys.SysUserService;
import org.shjr.iplat.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ShenHuaJie
 */
@Controller
@RequestMapping("/user")
public class SysUserController extends BaseController {
	@Autowired
	private SysUserService sysUserService;

	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelMap update(ModelMap modelMap, HttpServletRequest request,
			@RequestParam(value = "account", required = true) String account,
			@RequestParam(value = "password", required = true) String password) {
		SysUser sysUser = Request2ModelUtils.covert(SysUser.class, request);
		if (sysUser.getId() == null) {
			sysUser.setPassword(SecurityUtil.encryptSHA(password));
			sysUserService.add(sysUser);
		} else {
			sysUserService.update(sysUser);
		}
		return setSuccessModelMap(modelMap);
	}

	@ResponseBody
	@RequestMapping(value = "/update/password", method = RequestMethod.POST)
	public ModelMap updatePassword(ModelMap modelMap, HttpServletRequest request,
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "password", required = true) String password) {
		SysUser sysUser = sysUserService.queryById(id);
		if (sysUser == null) {
			throw new ParameterException("用户Id错误");
		}
		sysUser.setPassword(SecurityUtil.encryptSHA(password));
		sysUserService.update(sysUser);
		return setSuccessModelMap(modelMap);
	}

	@ResponseBody
	@RequestMapping(value = "/read/list")
	public ModelMap get(ModelMap modelMap, HttpServletRequest request) {
		Map<String, Object> params = WebUtil.getParameterMap(request);
		List<?> list = sysUserService.query(params);
		return setSuccessModelMap(modelMap, list);
	}
}
