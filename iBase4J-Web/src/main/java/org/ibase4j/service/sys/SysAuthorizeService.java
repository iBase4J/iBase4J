package org.ibase4j.service.sys;

import java.util.List;

import org.ibase4j.core.util.WebUtil;
import org.ibase4j.model.generator.SysRoleMenu;
import org.ibase4j.model.generator.SysUserMenu;
import org.ibase4j.model.generator.SysUserRole;
import org.ibase4j.model.sys.SysMenuBean;
import org.ibase4j.provider.sys.SysAuthorizeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:16:00
 */
@Service
public class SysAuthorizeService {
	@Autowired
	private SysAuthorizeProvider sysAuthorizeProvider;

	public List<SysMenuBean> queryAuthorizeByUserId(String id) {
		return sysAuthorizeProvider.queryAuthorizeByUserId(id);
	}

	public List<String> queryPermissionByUserId(String userId) {
		return sysAuthorizeProvider.queryPermissionByUserId(userId);
	}

	public void updateUserMenu(List<SysUserMenu> sysUserMenus) {
	    String userId = WebUtil.getCurrentUser();
		for (SysUserMenu sysUserMenu : sysUserMenus) {
			sysUserMenu.setCreateBy(userId);
			sysUserMenu.setUpdateBy(userId);
		}
		sysAuthorizeProvider.updateUserMenu(sysUserMenus);
	}

	public void updateUserRole(List<SysUserRole> sysUserRoles) {
	    String userId = WebUtil.getCurrentUser();
		for (SysUserRole sysUserRole : sysUserRoles) {
			sysUserRole.setCreateBy(userId);
			sysUserRole.setUpdateBy(userId);
		}
		sysAuthorizeProvider.updateUserRole(sysUserRoles);
	}

	public void updateRoleMenu(List<SysRoleMenu> sysRoleMenus) {
	    String userId = WebUtil.getCurrentUser();
		for (SysRoleMenu sysRoleMenu : sysRoleMenus) {
			sysRoleMenu.setCreateBy(userId);
			sysRoleMenu.setUpdateBy(userId);
		}
		sysAuthorizeProvider.updateRoleMenu(sysRoleMenus);
	}
}
