package org.ibase4j.service.sys;

import java.util.List;
import java.util.Map;

import org.ibase4j.model.sys.SysMenu;
import org.ibase4j.model.sys.SysRoleMenu;
import org.ibase4j.model.sys.SysUserMenu;
import org.ibase4j.model.sys.SysUserRole;

/**
 * @author ShenHuaJie
 * @since 2018年4月24日 上午10:59:37
 */
public interface SysAuthorizeService {

    List<String> queryMenuIdsByUserId(Long userId);

    void updateUserMenu(List<SysUserMenu> sysUserMenus);

    void updateUserPermission(List<SysUserMenu> sysUserMenus);

    List<SysUserRole> getRolesByUserId(Long userId);

    void updateUserRole(List<SysUserRole> sysUserRoles);

    List<String> queryMenuIdsByRoleId(Long roleId);

    void updateRoleMenu(List<SysRoleMenu> sysRoleMenus);

    void updateRolePermission(List<SysRoleMenu> sysRoleMenus);

    List<SysMenu> queryAuthorizeByUserId(Long userId);

    List<SysMenu> queryMenusPermission();

    List<String> queryPermissionByUserId(Long userId);

    List<String> queryRolePermission(Long roleId);

    List<String> queryUserPermission(Long userId);

    List<Map<String, Object>> queryUserPermissions(SysUserMenu record);

    List<Map<String, Object>> queryRolePermissions(SysRoleMenu record);
}
