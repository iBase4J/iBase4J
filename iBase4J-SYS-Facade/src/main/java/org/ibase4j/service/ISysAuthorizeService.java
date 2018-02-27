package org.ibase4j.service;

import java.util.List;
import java.util.Map;

import org.ibase4j.model.SysMenu;
import org.ibase4j.model.SysRoleMenu;
import org.ibase4j.model.SysUserMenu;
import org.ibase4j.model.SysUserRole;

import top.ibase4j.core.base.IBaseService;

public interface ISysAuthorizeService extends IBaseService<SysMenu> {

	public List<String> queryMenuIdsByUserId(Long userId);

	public void updateUserMenu(List<SysUserMenu> sysUserMenus);

	public void updateUserPermission(List<SysUserMenu> sysUserMenus);

	public List<SysUserRole> getRolesByUserId(Long userId);

	public void updateUserRole(List<SysUserRole> sysUserRoles);

	public List<String> queryMenuIdsByRoleId(Long roleId);

	public void updateRoleMenu(List<SysRoleMenu> sysRoleMenus);

	public void updateRolePermission(List<SysRoleMenu> sysRoleMenus);

	public List<SysMenu> queryAuthorizeByUserId(Long userId);

	public List<SysMenu> queryMenusPermission();

	public List<String> queryPermissionByUserId(Long userId);

	public List<Map<String, Object>> queryUserPermissions(SysUserMenu record);

	public List<Map<String, Object>> queryRolePermissions(SysRoleMenu record);

	public List<String> queryRolePermission(Long id);

	public List<String> queryUserPermission(Long id);
}
