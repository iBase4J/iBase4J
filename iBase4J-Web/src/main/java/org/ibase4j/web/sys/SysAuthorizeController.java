package org.ibase4j.web.sys;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.core.base.BaseController;
import org.ibase4j.core.util.Request2ListUtil;
import org.ibase4j.model.sys.SysRoleMenu;
import org.ibase4j.model.sys.SysUserMenu;
import org.ibase4j.model.sys.SysUserRole;
import org.ibase4j.service.sys.SysAuthorizeService;
import org.ibase4j.service.sys.SysCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 权限管理
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:14:05
 */
@RestController
@Api(value = "权限管理", description = "权限管理")
public class SysAuthorizeController extends BaseController {
	@Autowired
	private SysAuthorizeService authorizeService;
	@Autowired
	private SysCacheService sysCacheService;

	@ApiOperation(value = "修改用户菜单")
	@RequiresPermissions("user.menu.update")
	@RequestMapping(value = "/user/update/menu", method = RequestMethod.POST)
	public Object userMenu(HttpServletRequest request, ModelMap modelMap) {
		List<SysUserMenu> list = Request2ListUtil.covert(SysUserMenu.class, request);
		authorizeService.updateUserMenu(list);
		return setSuccessModelMap(modelMap);
	}

	@ApiOperation(value = "修改用户角色")
	@RequiresPermissions("user.role.update")
	@RequestMapping(value = "/user/update/role", method = RequestMethod.POST)
	public Object userRole(HttpServletRequest request, ModelMap modelMap) {
		List<SysUserRole> list = Request2ListUtil.covert(SysUserRole.class, request);
		authorizeService.updateUserRole(list);
		return setSuccessModelMap(modelMap);
	}

	@ApiOperation(value = "修改角色菜单")
	@RequiresPermissions("role.menu.update")
	@RequestMapping(value = "/role/update/menu", method = RequestMethod.POST)
	public Object roleMenu(HttpServletRequest request, ModelMap modelMap) {
		List<SysRoleMenu> list = Request2ListUtil.covert(SysRoleMenu.class, request);
		authorizeService.updateRoleMenu(list);
		return setSuccessModelMap(modelMap);
	}

	@ApiOperation(value = "清理缓存")
	@RequiresPermissions("sys.cache.update")
	@RequestMapping(value = "/cache/update", method = RequestMethod.POST)
	public Object flush(HttpServletRequest request, ModelMap modelMap) {
		sysCacheService.flushCache();
		return setSuccessModelMap(modelMap);
	}
}
