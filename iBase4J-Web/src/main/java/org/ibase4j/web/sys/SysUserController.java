/**
 * 
 */
package org.ibase4j.web.sys;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.core.base.BaseController;
import org.ibase4j.core.util.Request2ModelUtil;
import org.ibase4j.core.util.UploadUtil;
import org.ibase4j.core.util.WebUtil;
import org.ibase4j.model.generator.SysUser;
import org.ibase4j.model.sys.SysMenuBean;
import org.ibase4j.service.sys.SysAuthorizeService;
import org.ibase4j.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户管理控制器
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:12:12
 */
@RestController
@Api(value = "用户管理", description = "用户管理")
@RequestMapping(value = "/user", method = RequestMethod.POST)
public class SysUserController extends BaseController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysAuthorizeService authorizeService;

	// 修改用户信息
	@ApiOperation(value = "修改用户信息")
	@RequiresPermissions("sys:user:update")
	@RequestMapping(value = "/update")
	public Object update(HttpServletRequest request, ModelMap modelMap) {
		SysUser sysUser = Request2ModelUtil.covert(SysUser.class, request);
		if (StringUtils.isNotBlank(sysUser.getAvatar())) {
			String avatar = UploadUtil.remove2DFS(UploadUtil.getUploadDir(request) + sysUser.getAvatar());
			sysUser.setAvatar(avatar);
		}
		sysUserService.updateUserInfo(sysUser);
		return setSuccessModelMap(modelMap);
	}

	// 修改密码
	@ApiOperation(value = "修改密码")
	@RequestMapping(value = "/update/password")
	public Object updatePassword(ModelMap modelMap, @RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "password", required = false) String password) {
		sysUserService.updatePassword(id, password);
		return setSuccessModelMap(modelMap);
	}

	// 查询用户
	@ApiOperation(value = "查询用户")
	@RequiresPermissions("sys:user:read")
	@RequestMapping(value = "/read/list")
	public Object get(HttpServletRequest request, ModelMap modelMap) {
		Map<String, Object> params = WebUtil.getParameterMap(request);
		PageInfo<?> list = sysUserService.queryBeans(params);
		return setSuccessModelMap(modelMap, list);
	}

	// 用户详细信息
	@ApiOperation(value = "用户详细信息")
	@RequiresPermissions("sys:user:read")
	@RequestMapping(value = "/read/detail")
	public Object detail(ModelMap modelMap, @RequestParam(value = "id", required = false) Integer id) {
		SysUser sysUser = sysUserService.queryById(id);
		if (sysUser != null) {
			sysUser.setPassword(null);
		}
		return setSuccessModelMap(modelMap, sysUser);
	}

	// 当前用户
	@ApiOperation(value = "当前用户信息")
	@RequestMapping(value = "/read/current")
	public Object current(ModelMap modelMap) {
		Integer id = getCurrUser();
		SysUser sysUser = sysUserService.queryById(id);
		if (sysUser != null) {
			sysUser.setPassword(null);
		}
		List<SysMenuBean> menus = authorizeService.queryAuthorizeByUserId(id);
		modelMap.put("user", sysUser);
		modelMap.put("menus", menus);
		return setSuccessModelMap(modelMap);
	}
}
