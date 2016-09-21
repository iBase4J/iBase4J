package org.ibase4j.provider.sys;

import java.util.List;

import org.ibase4j.core.base.BaseProvider;
import org.ibase4j.model.sys.SysMenu;
import org.ibase4j.model.sys.SysRoleMenu;
import org.ibase4j.model.sys.SysUserMenu;
import org.ibase4j.model.sys.SysUserRole;
import org.ibase4j.model.sys.ext.SysMenuBean;

public interface ISysAuthorizeProvider extends BaseProvider<SysMenu> {

    public void updateUserMenu(List<SysUserMenu> sysUserMenus);

    public void updateUserRole(List<SysUserRole> sysUserRoles);

    public void updateRoleMenu(List<SysRoleMenu> sysRoleMenus);

    public List<SysMenuBean> queryAuthorizeByUserId(String userId);

    public List<String> queryPermissionByUserId(String userId);
}
