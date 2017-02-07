package org.ibase4j.provider.sys;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.core.util.DataUtil;
import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.dao.sys.SysAuthorizeMapper;
import org.ibase4j.dao.sys.SysRoleMenuMapper;
import org.ibase4j.dao.sys.SysUserMenuMapper;
import org.ibase4j.dao.sys.SysUserRoleMapper;
import org.ibase4j.model.sys.SysMenu;
import org.ibase4j.model.sys.SysRoleMenu;
import org.ibase4j.model.sys.SysUserMenu;
import org.ibase4j.model.sys.SysUserRole;
import org.ibase4j.model.sys.ext.SysMenuBean;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@CacheConfig(cacheNames = "sysAuthorize")
@DubboService(interfaceClass = ISysAuthorizeProvider.class)
public class SysAuthorizeProviderImpl extends BaseProviderImpl<SysMenu> implements ISysAuthorizeProvider {
	@Autowired
	private SysUserMenuMapper sysUserMenuMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;
	@Autowired
	private SysAuthorizeMapper sysAuthorizeMapper;
	@Autowired
	private ISysMenuProvider sysMenuProvider;

	public List<Long> queryMenuIdsByUserId(Long userId) {
		return sysUserMenuMapper.queryMenuIdsByUserId(userId);
	}

	@Transactional
	@CacheEvict(value = { "getAuthorize", "sysPermission" }, allEntries = true)
	public void updateUserMenu(List<SysUserMenu> sysUserMenus) {
		Long userId = null;
		for (SysUserMenu sysUserMenu : sysUserMenus) {
			if (sysUserMenu.getUserId() != null && "read".equals(sysUserMenu.getPermission())) {
				userId = sysUserMenu.getUserId();
			}
		}
		if (userId != null) {
			sysAuthorizeMapper.deleteUserMenu(userId, "read");
		}
		for (SysUserMenu sysUserMenu : sysUserMenus) {
			if (sysUserMenu.getUserId() != null && sysUserMenu.getMenuId() != null
					&& "read".equals(sysUserMenu.getPermission())) {
				sysUserMenuMapper.insert(sysUserMenu);
			}
		}
	}

	@Transactional
	@CacheEvict(value = { "getAuthorize", "sysPermission" }, allEntries = true)
	public void updateUserPermission(List<SysUserMenu> sysUserMenus) {
		Long userId = null;
		String permission = null;
		for (SysUserMenu sysUserMenu : sysUserMenus) {
			if (sysUserMenu.getUserId() != null && !"read".equals(sysUserMenu.getPermission())) {
				userId = sysUserMenu.getUserId();
				permission = sysUserMenu.getPermission();
			}
		}
		if (userId != null && DataUtil.isNotEmpty(permission)) {
			sysAuthorizeMapper.deleteUserMenu(userId, permission);
		}
		for (SysUserMenu sysUserMenu : sysUserMenus) {
			if (sysUserMenu.getUserId() != null && sysUserMenu.getMenuId() != null
					&& !"read".equals(sysUserMenu.getPermission())) {
				sysUserMenuMapper.insert(sysUserMenu);
			}
		}
	}

	public List<SysUserRole> getRolesByUserId(Long userId) {
		SysUserRole sysUserRole = new SysUserRole(userId, null);
		Wrapper<SysUserRole> wrapper = new EntityWrapper<SysUserRole>(sysUserRole);
		List<SysUserRole> userRoles = sysUserRoleMapper.selectList(wrapper);
		return userRoles;
	}

	@Transactional
	@CacheEvict(value = { "getAuthorize", "sysPermission" }, allEntries = true)
	public void updateUserRole(List<SysUserRole> sysUserRoles) {
		Long userId = null;
		for (SysUserRole sysUserRole : sysUserRoles) {
			if (sysUserRole.getUserId() != null) {
				userId = sysUserRole.getUserId();
				break;
			}
		}
		if (userId != null) {
			sysAuthorizeMapper.deleteUserRole(userId);
		}
		for (SysUserRole sysUserRole : sysUserRoles) {
			if (sysUserRole.getUserId() != null && sysUserRole.getRoleId() != null) {
				sysUserRoleMapper.insert(sysUserRole);
			}
		}
	}

	public List<Long> queryMenuIdsByRoleId(Long roleId) {
		return sysRoleMenuMapper.queryMenuIdsByRoleId(roleId);
	}

	@Transactional
	@CacheEvict(value = { "getAuthorize", "sysPermission" }, allEntries = true)
	public void updateRoleMenu(List<SysRoleMenu> sysRoleMenus) {
		Long roleId = null;
		for (SysRoleMenu sysRoleMenu : sysRoleMenus) {
			if (sysRoleMenu.getRoleId() != null && "read".equals(sysRoleMenu.getPermission())) {
				roleId = sysRoleMenu.getRoleId();
				break;
			}
		}
		if (roleId != null) {
			sysAuthorizeMapper.deleteRoleMenu(roleId, "read");
		}
		for (SysRoleMenu sysRoleMenu : sysRoleMenus) {
			if (sysRoleMenu.getRoleId() != null && sysRoleMenu.getMenuId() != null
					&& "read".equals(sysRoleMenu.getPermission())) {
				sysRoleMenuMapper.insert(sysRoleMenu);
			}
		}
	}

	@Transactional
	@CacheEvict(value = { "getAuthorize", "sysPermission" }, allEntries = true)
	public void updateRolePermission(List<SysRoleMenu> sysRoleMenus) {
		Long roleId = null;
		String permission = null;
		for (SysRoleMenu sysRoleMenu : sysRoleMenus) {
			if (sysRoleMenu.getRoleId() != null && !"read".equals(sysRoleMenu.getPermission())) {
				roleId = sysRoleMenu.getRoleId();
				permission = sysRoleMenu.getPermission();
				break;
			}
		}
		if (roleId != null && DataUtil.isNotEmpty(permission)) {
			sysAuthorizeMapper.deleteRoleMenu(roleId, permission);
		}
		for (SysRoleMenu sysRoleMenu : sysRoleMenus) {
			if (sysRoleMenu.getRoleId() != null && sysRoleMenu.getMenuId() != null
					&& !"read".equals(sysRoleMenu.getPermission())) {
				sysRoleMenuMapper.insert(sysRoleMenu);
			}
		}
	}

	@Cacheable(value = "getAuthorize")
	public List<SysMenuBean> queryAuthorizeByUserId(Long userId) {
		List<Long> menuIds = sysAuthorizeMapper.getAuthorize(userId);
		List<SysMenuBean> menus = sysMenuProvider.getList(menuIds, SysMenuBean.class);
		Map<Long, List<SysMenuBean>> map = InstanceUtil.newHashMap();
		for (SysMenuBean sysMenuBean : menus) {
			if (map.get(sysMenuBean.getParentId()) == null) {
				List<SysMenuBean> menuBeans = InstanceUtil.newArrayList();
				map.put(sysMenuBean.getParentId(), menuBeans);
			}
			map.get(sysMenuBean.getParentId()).add(sysMenuBean);
		}
		List<SysMenuBean> result = InstanceUtil.newArrayList();
		for (SysMenuBean sysMenuBean : menus) {
			if (sysMenuBean.getParentId() == 0) {
				sysMenuBean.setLeaf(0);
				sysMenuBean.setMenuBeans(getChildMenu(map, sysMenuBean.getId()));
				result.add(sysMenuBean);
			}
		}
		return result;
	}

	// 递归获取子菜单
	private List<SysMenuBean> getChildMenu(Map<Long, List<SysMenuBean>> map, Long id) {
		List<SysMenuBean> menus = map.get(id);
		if (menus != null) {
			for (SysMenuBean sysMenuBean : menus) {
				sysMenuBean.setMenuBeans(getChildMenu(map, sysMenuBean.getId()));
			}
		}
		return menus;
	}

	@Cacheable("sysPermission")
	public List<String> queryPermissionByUserId(Long userId) {
		return sysAuthorizeMapper.queryPermissionByUserId(userId);
	}

	public List<SysMenuBean> queryMenusPermission() {
		return sysAuthorizeMapper.queryMenusPermission();
	}

	public List<Long> queryUserPermissions(Long userId, String permission) {
		return sysUserMenuMapper.queryPermissions(userId, permission);
	}

	public List<Long> queryRolePermissions(Long roleId, String permission) {
		return sysRoleMenuMapper.queryPermissions(roleId, permission);
	}
}
