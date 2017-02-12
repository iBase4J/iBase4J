package org.ibase4j.service.sys;

import java.util.List;
import java.util.Map;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@Service
@CacheConfig(cacheNames = "sysAuthorize")
public class SysAuthorizeService {
	@Autowired
	private SysUserMenuMapper sysUserMenuMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;
	@Autowired
	private SysAuthorizeMapper sysAuthorizeMapper;
	@Autowired
	private SysMenuService sysMenuService;

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
	public List<SysMenu> queryAuthorizeByUserId(Long userId) {
		List<Long> menuIds = sysAuthorizeMapper.getAuthorize(userId);
		List<SysMenu> menus = sysMenuService.getList(menuIds);
		Map<Long, List<SysMenu>> map = InstanceUtil.newHashMap();
		for (SysMenu sysMenu : menus) {
			if (map.get(sysMenu.getParentId()) == null) {
				List<SysMenu> menuBeans = InstanceUtil.newArrayList();
				map.put(sysMenu.getParentId(), menuBeans);
			}
			map.get(sysMenu.getParentId()).add(sysMenu);
		}
		List<SysMenu> result = InstanceUtil.newArrayList();
		for (SysMenu sysMenu : menus) {
			if (sysMenu.getParentId() == 0) {
				sysMenu.setLeaf(0);
				sysMenu.setMenuBeans(getChildMenu(map, sysMenu.getId()));
				result.add(sysMenu);
			}
		}
		return result;
	}

	// 递归获取子菜单
	private List<SysMenu> getChildMenu(Map<Long, List<SysMenu>> map, Long id) {
		List<SysMenu> menus = map.get(id);
		if (menus != null) {
			for (SysMenu sysMenu : menus) {
				sysMenu.setMenuBeans(getChildMenu(map, sysMenu.getId()));
			}
		}
		return menus;
	}

	@Cacheable("sysPermission")
	public List<String> queryPermissionByUserId(Long userId) {
		return sysAuthorizeMapper.queryPermissionByUserId(userId);
	}

	public List<SysMenu> queryMenusPermission() {
		return sysAuthorizeMapper.queryMenusPermission();
	}

	public List<Long> queryUserPermissions(SysUserMenu sysUserMenu) {
		return sysUserMenuMapper.queryPermissions(sysUserMenu.getUserId(), sysUserMenu.getPermission());
	}

	public List<Long> queryRolePermissions(SysRoleMenu sysRoleMenu) {
		return sysRoleMenuMapper.queryPermissions(sysRoleMenu.getRoleId(), sysRoleMenu.getPermission());
	}
}
