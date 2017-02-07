package org.ibase4j.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ibase4j.core.exception.IllegalParameterException;
import org.ibase4j.core.util.WebUtil;
import org.ibase4j.model.sys.SysRoleMenu;
import org.ibase4j.model.sys.SysUserMenu;
import org.ibase4j.model.sys.SysUserRole;
import org.ibase4j.model.sys.ext.SysMenuBean;
import org.ibase4j.provider.sys.ISysAuthorizeProvider;

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

	public List<Long> queryMenuIdsByUserId(Long userId) {
		return sysAuthorizeProvider.queryMenuIdsByUserId(userId);
	}

	public List<Long> queryMenuIdsByRoleId(Long roleId) {
		return sysAuthorizeProvider.queryMenuIdsByRoleId(roleId);
	}

	public List<SysUserRole> getRolesByUserId(Long userId) {
		return sysAuthorizeProvider.getRolesByUserId(userId);
	}

	public void updateUserMenu(List<SysUserMenu> sysUserMenus) {
		Long userId = null;
		Long currentUserId = WebUtil.getCurrentUser();
		for (SysUserMenu sysUserMenu : sysUserMenus) {
			if (sysUserMenu.getUserId() != null) {
				if (userId != null && userId != sysUserMenu.getUserId()) {
					throw new IllegalParameterException("参数错误.");
				}
				userId = sysUserMenu.getUserId();
			}
			sysUserMenu.setCreateBy(currentUserId);
			sysUserMenu.setUpdateBy(currentUserId);
		}
		sysAuthorizeProvider.updateUserMenu(sysUserMenus);
	}

	public void updateUserRole(List<SysUserRole> sysUserRoles) {
		Long userId = null;
		Long currentUserId = WebUtil.getCurrentUser();
		for (SysUserRole sysUserRole : sysUserRoles) {
			if (sysUserRole.getUserId() != null) {
				if (userId != null && userId != sysUserRole.getUserId()) {
					throw new IllegalParameterException("参数错误.");
				}
				userId = sysUserRole.getUserId();
			}
			sysUserRole.setCreateBy(currentUserId);
			sysUserRole.setUpdateBy(currentUserId);
		}
		sysAuthorizeProvider.updateUserRole(sysUserRoles);
	}

	public void updateRoleMenu(List<SysRoleMenu> sysRoleMenus) {
		Long roleId = null;
		Long userId = WebUtil.getCurrentUser();
		for (SysRoleMenu sysRoleMenu : sysRoleMenus) {
			if (sysRoleMenu.getRoleId() != null) {
				if (roleId != null && roleId != sysRoleMenu.getRoleId()) {
					throw new IllegalParameterException("参数错误.");
				}
				roleId = sysRoleMenu.getRoleId();
			}
			sysRoleMenu.setCreateBy(userId);
			sysRoleMenu.setUpdateBy(userId);
		}
		sysAuthorizeProvider.updateRoleMenu(sysRoleMenus);
	}

	public List<SysMenuBean> queryMenusPermission() {
		return sysAuthorizeProvider.queryMenusPermission();
	}

	public List<Long> queryUserPermissions(Long userId, String permission) {
		return sysAuthorizeProvider.queryUserPermissions(userId, permission);
	}

	public List<Long> queryRolePermissions(Long roleId, String permission) {
		return sysAuthorizeProvider.queryRolePermissions(roleId, permission);
	}

	public void updateUserPermission(List<SysUserMenu> sysUserMenus) {
		sysAuthorizeProvider.updateUserPermission(sysUserMenus);
	}

	public void updateRolePermission(List<SysRoleMenu> sysRoleMenus) {
		sysAuthorizeProvider.updateRolePermission(sysRoleMenus);
	}

	/** 获取用户权限 */
	public List<String> queryPermissionByUserId(Long userId) {
		return sysAuthorizeProvider.queryPermissionByUserId(userId);
	}
}
