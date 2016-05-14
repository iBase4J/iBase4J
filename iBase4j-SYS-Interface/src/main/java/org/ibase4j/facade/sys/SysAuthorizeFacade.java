package org.ibase4j.facade.sys;

import java.util.List;

import org.ibase4j.mybatis.generator.model.SysRoleMenu;
import org.ibase4j.mybatis.generator.model.SysUserMenu;
import org.ibase4j.mybatis.generator.model.SysUserRole;
import org.ibase4j.mybatis.sys.model.SysMenuBean;

public interface SysAuthorizeFacade {

	public void updateUserMenu(List<SysUserMenu> sysUserMenus);

	public void updateUserRole(List<SysUserRole> sysUserRoles);

	public void updateRoleMenu(List<SysRoleMenu> sysRoleMenus);

	public List<SysMenuBean> getAuthorize(Integer userId);
}
