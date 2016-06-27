package org.ibase4j.service.sys;

import java.util.List;

import org.ibase4j.core.support.dubbo.spring.annotation.DubboReference;
import org.ibase4j.core.util.WebUtil;
import org.ibase4j.model.generator.SysRoleMenu;
import org.ibase4j.model.generator.SysUserMenu;
import org.ibase4j.model.generator.SysUserRole;
import org.ibase4j.model.sys.SysMenuBean;
import org.ibase4j.provider.sys.SysAuthorizeProvider;
import org.ibase4j.provider.sys.SysCacheProvider;
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
	@DubboReference
	private SysCacheProvider sysCacheProvider;

	public List<SysMenuBean> queryAuthorizeByUserId(Integer id) {
		return sysAuthorizeProvider.queryAuthorizeByUserId(id);
	}

	public List<String> queryPermissionByUserId(Integer userId) {
		return sysAuthorizeProvider.queryPermissionByUserId(userId);
	}

	public void flushCache() {
		sysCacheProvider.flush();
	}

	public void updateUserMenu(List<SysUserMenu> sysUserMenus) {
		Integer userId = WebUtil.getCurrentUser();
		for (SysUserMenu sysUserMenu : sysUserMenus) {
			sysUserMenu.setCreateBy(userId);
			sysUserMenu.setUpdateBy(userId);
		}
		sysAuthorizeProvider.updateUserMenu(sysUserMenus);
	}

	public void updateUserRole(List<SysUserRole> sysUserRoles) {
		Integer userId = WebUtil.getCurrentUser();
		for (SysUserRole sysUserRole : sysUserRoles) {
			sysUserRole.setCreateBy(userId);
			sysUserRole.setUpdateBy(userId);
		}
		sysAuthorizeProvider.updateUserRole(sysUserRoles);
	}

	public void updateRoleMenu(List<SysRoleMenu> sysRoleMenus) {
		Integer userId = WebUtil.getCurrentUser();
		for (SysRoleMenu sysRoleMenu : sysRoleMenus) {
			sysRoleMenu.setCreateBy(userId);
			sysRoleMenu.setUpdateBy(userId);
		}
		sysAuthorizeProvider.updateRoleMenu(sysRoleMenus);
	}
}
