package org.ibase4j.service.sys;

import java.util.List;

import org.ibase4j.core.util.WebUtil;
import org.ibase4j.model.sys.SysRoleMenu;
import org.ibase4j.model.sys.SysUserMenu;
import org.ibase4j.model.sys.SysUserRole;
import org.ibase4j.model.sys.ext.SysMenuBean;
import org.ibase4j.provider.sys.ISysAuthorizeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:16:00
 */
@Service
public class SysAuthorizeService {
	@Autowired
	private ISysAuthorizeProvider sysAuthorizeProvider;

	public List<SysMenuBean> queryAuthorizeByUserId(Long id) {
		return sysAuthorizeProvider.queryAuthorizeByUserId(id);
	}

	public List<String> queryPermissionByUserId(Long userId) {
		return sysAuthorizeProvider.queryPermissionByUserId(userId);
	}

	public void updateUserMenu(List<SysUserMenu> sysUserMenus) {
		Long userId = WebUtil.getCurrentUser();
		for (SysUserMenu sysUserMenu : sysUserMenus) {
			sysUserMenu.setCreateBy(userId);
			sysUserMenu.setUpdateBy(userId);
		}
		sysAuthorizeProvider.updateUserMenu(sysUserMenus);
	}

	public void updateUserRole(List<SysUserRole> sysUserRoles) {
		Long userId = WebUtil.getCurrentUser();
		for (SysUserRole sysUserRole : sysUserRoles) {
			sysUserRole.setCreateBy(userId);
			sysUserRole.setUpdateBy(userId);
		}
		sysAuthorizeProvider.updateUserRole(sysUserRoles);
	}

	public void updateRoleMenu(List<SysRoleMenu> sysRoleMenus) {
		Long userId = WebUtil.getCurrentUser();
		for (SysRoleMenu sysRoleMenu : sysRoleMenus) {
			sysRoleMenu.setCreateBy(userId);
			sysRoleMenu.setUpdateBy(userId);
		}
		sysAuthorizeProvider.updateRoleMenu(sysRoleMenus);
	}
}
