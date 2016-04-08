package org.shjr.ibase4j.service.sys;

import java.util.List;

import org.shjr.ibase4j.mybatis.generator.dao.SysRoleMenuMapper;
import org.shjr.ibase4j.mybatis.generator.dao.SysUserMenuMapper;
import org.shjr.ibase4j.mybatis.generator.dao.SysUserRoleMapper;
import org.shjr.ibase4j.mybatis.generator.model.SysMenu;
import org.shjr.ibase4j.mybatis.generator.model.SysRoleMenu;
import org.shjr.ibase4j.mybatis.generator.model.SysUserMenu;
import org.shjr.ibase4j.mybatis.generator.model.SysUserRole;
import org.shjr.ibase4j.mybatis.sys.dao.SysAuthorizeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysAuthorizeService {
	@Autowired
	private SysUserMenuMapper sysUserMenuMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;
	@Autowired
	private SysAuthorizeMapper sysAuthorizeMapper;

	@Transactional
	@CacheEvict("getAuthorize")
	public void updateUserMenu(List<SysUserMenu> sysUserMenus) {
		sysAuthorizeMapper.deleteUserMenu(sysUserMenus.get(0).getUserId());
		for (SysUserMenu sysUserMenu : sysUserMenus) {
			sysUserMenuMapper.insert(sysUserMenu);
		}
	}

	@Transactional
	@CacheEvict("getAuthorize")
	public void updateUserRole(List<SysUserRole> sysUserRoles) {
		sysAuthorizeMapper.deleteUserRole(sysUserRoles.get(0).getUserId());
		for (SysUserRole sysUserRole : sysUserRoles) {
			sysUserRoleMapper.insert(sysUserRole);
		}
	}

	@Transactional
	@CacheEvict("getAuthorize")
	public void updateRoleMenu(List<SysRoleMenu> sysRoleMenus) {
		sysAuthorizeMapper.deleteRoleMenu(sysRoleMenus.get(0).getRoleId());
		for (SysRoleMenu sysRoleMenu : sysRoleMenus) {
			sysRoleMenuMapper.insert(sysRoleMenu);
		}
	}

	@Cacheable("getAuthorize")
	public List<SysMenu> getAuthorize(Integer userId) {
		return sysAuthorizeMapper.getAuthorize(userId);
	}
}
