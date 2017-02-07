package org.ibase4j.provider.sys;

import java.util.List;

import org.ibase4j.core.base.BaseProvider;
import org.ibase4j.model.sys.SysMenu;
import org.ibase4j.model.sys.SysRoleMenu;
import org.ibase4j.model.sys.SysUserMenu;
import org.ibase4j.model.sys.SysUserRole;

public interface ISysAuthorizeProvider extends BaseProvider<SysMenu> {

	public List<Long> queryMenuIdsByUserId(Long userId);

	public void updateUserMenu(List<SysUserMenu> sysUserMenus);

	public void updateUserPermission(List<SysUserMenu> sysUserMenus);

	public List<SysUserRole> getRolesByUserId(Long userId);

	public void updateUserRole(List<SysUserRole> sysUserRoles);

	public List<Long> queryMenuIdsByRoleId(Long roleId);

	public void updateRoleMenu(List<SysRoleMenu> sysRoleMenus);

	public void updateRolePermission(List<SysRoleMenu> sysRoleMenus);

	public List<SysMenu> queryAuthorizeByUserId(Long userId);

	public List<SysMenu> queryMenusPermission();

	public List<Long> queryUserPermissions(Long userId, String permission);

	public List<Long> queryRolePermissions(Long roleId, String permission);

	public List<String> queryPermissionByUserId(Long userId);
}
