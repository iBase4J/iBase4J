package org.ibase4j.web.sys;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.ibase4j.core.base.BaseController;
import org.ibase4j.model.sys.SysRoleMenu;
import org.ibase4j.model.sys.SysUserMenu;
import org.ibase4j.model.sys.SysUserRole;
import org.ibase4j.service.sys.SysAuthorizeService;
import org.ibase4j.service.sys.SysCacheService;

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

	@ApiOperation(value = "获取用户菜单编号")
	@PutMapping(value = "user/read/menu")
	@RequiresPermissions("sys.permisson.userMenu.read")
	public Object getUserMenu(ModelMap modelMap, @RequestBody SysUserMenu record) {
		List<Long> menus = authorizeService.queryMenuIdsByUserId(record.getUserId());
		return setSuccessModelMap(modelMap, menus);
	}

	@ApiOperation(value = "修改用户菜单")
	@PostMapping(value = "/user/update/menu")
	@RequiresPermissions("sys.permisson.userMenu.update")
	public Object userMenu(ModelMap modelMap, @RequestBody List<SysUserMenu> list) {
		authorizeService.updateUserMenu(list);
		return setSuccessModelMap(modelMap);
	}

	@ApiOperation(value = "获取用户角色")
	@PutMapping(value = "user/read/role")
	@RequiresPermissions("sys.permisson.userRole.read")
	public Object getUserRole(ModelMap modelMap, @RequestBody SysUserRole record) {
		List<SysUserRole> menus = authorizeService.getRolesByUserId(record.getUserId());
		return setSuccessModelMap(modelMap, menus);
	}

	@ApiOperation(value = "修改用户角色")
	@PostMapping(value = "/user/update/role")
	@RequiresPermissions("sys.permisson.userRole.update")
	public Object userRole(ModelMap modelMap, @RequestBody List<SysUserRole> list) {
		authorizeService.updateUserRole(list);
		return setSuccessModelMap(modelMap);
	}

	@ApiOperation(value = "获取角色菜单编号")
	@PutMapping(value = "role/read/menu")
	@RequiresPermissions("sys.permisson.roleMenu.read")
	public Object getRoleMenu(ModelMap modelMap, @RequestBody SysRoleMenu record) {
		List<Long> menus = authorizeService.queryMenuIdsByRoleId(record.getRoleId());
		return setSuccessModelMap(modelMap, menus);
	}

	@ApiOperation(value = "修改角色菜单")
	@PostMapping(value = "/role/update/menu")
	@RequiresPermissions("sys.permisson.roleMenu.update")
	public Object roleMenu(ModelMap modelMap, @RequestBody List<SysRoleMenu> list) {
		authorizeService.updateRoleMenu(list);
		return setSuccessModelMap(modelMap);
	}

	@ApiOperation(value = "获取人员操作权限")
	@PutMapping(value = "user/read/permission")
	@RequiresPermissions("sys.permisson.user.read")
	public Object queryUserPermissions(ModelMap modelMap, @RequestBody SysUserMenu record) {
		List<Long> menuIds = authorizeService.queryUserPermissions(record.getUserId(), record.getPermission());
		return setSuccessModelMap(modelMap, menuIds);
	}

	@ApiOperation(value = "修改用户操作权限")
	@PostMapping(value = "/user/update/permission")
	@RequiresPermissions("sys.permisson.user.update")
	public Object updateUserPermission(ModelMap modelMap, @RequestBody List<SysUserMenu> list) {
		authorizeService.updateUserPermission(list);
		return setSuccessModelMap(modelMap);
	}

	@ApiOperation(value = "获取角色操作权限")
	@PutMapping(value = "role/read/permission")
	@RequiresPermissions("sys.permisson.role.read")
	public Object queryRolePermissions(ModelMap modelMap, @RequestBody SysRoleMenu record) {
		List<Long> menuIds = authorizeService.queryRolePermissions(record.getRoleId(), record.getPermission());
		return setSuccessModelMap(modelMap, menuIds);
	}

	@ApiOperation(value = "修改角色操作权限")
	@PostMapping(value = "/role/update/permission")
	@RequiresPermissions("sys.permisson.role.update")
	public Object updateRolePermission(ModelMap modelMap, @RequestBody List<SysRoleMenu> list) {
		authorizeService.updateRolePermission(list);
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
