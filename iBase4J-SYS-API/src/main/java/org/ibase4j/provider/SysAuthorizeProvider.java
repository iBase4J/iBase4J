package org.ibase4j.provider;

import java.util.List;

import org.ibase4j.model.generator.SysRoleMenu;
import org.ibase4j.model.generator.SysUserMenu;
import org.ibase4j.model.generator.SysUserRole;
import org.ibase4j.model.sys.SysMenuBean;

public interface SysAuthorizeProvider {

	public void updateUserMenu(List<SysUserMenu> sysUserMenus);

	public void updateUserRole(List<SysUserRole> sysUserRoles);

	public void updateRoleMenu(List<SysRoleMenu> sysRoleMenus);

	public List<SysMenuBean> queryAuthorizeByUserId(String userId);

	public List<String> queryPermissionByUserId(String userId);
}
