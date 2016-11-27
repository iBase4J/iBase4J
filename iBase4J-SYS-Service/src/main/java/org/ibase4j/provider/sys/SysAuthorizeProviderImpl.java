package org.ibase4j.provider.sys;

import java.util.List;
import java.util.Map;

import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
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
import org.ibase4j.provider.sys.ISysAuthorizeProvider;
import org.ibase4j.provider.sys.ISysMenuProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	@CacheEvict(value = { "getAuthorize", "sysPermission" }, allEntries = true)
	public void updateUserMenu(List<SysUserMenu> sysUserMenus) {
		sysAuthorizeMapper.deleteUserMenu(sysUserMenus.get(0).getUserId());
		for (SysUserMenu sysUserMenu : sysUserMenus) {
			sysUserMenuMapper.insert(sysUserMenu);
		}
	}

	@Transactional
	@CacheEvict(value = { "getAuthorize", "sysPermission" }, allEntries = true)
	public void updateUserRole(List<SysUserRole> sysUserRoles) {
		sysAuthorizeMapper.deleteUserRole(sysUserRoles.get(0).getUserId());
		for (SysUserRole sysUserRole : sysUserRoles) {
			sysUserRoleMapper.insert(sysUserRole);
		}
	}

	@Transactional
	@CacheEvict(value = { "getAuthorize", "sysPermission" }, allEntries = true)
	public void updateRoleMenu(List<SysRoleMenu> sysRoleMenus) {
		sysAuthorizeMapper.deleteRoleMenu(sysRoleMenus.get(0).getRoleId());
		for (SysRoleMenu sysRoleMenu : sysRoleMenus) {
			sysRoleMenuMapper.insert(sysRoleMenu);
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
}
