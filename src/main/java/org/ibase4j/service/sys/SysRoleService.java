package org.ibase4j.service.sys;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.ibase4j.mapper.sys.SysRoleMapper;
import org.ibase4j.model.sys.SysDept;
import org.ibase4j.model.sys.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import top.ibase4j.core.base.BaseService;
import top.ibase4j.core.support.Pagination;

/**
 * @author ShenHuaJie
 * @version 2016年5月31日 上午11:01:33
 */
@Service
@CacheConfig(cacheNames = "sysRole")
public class SysRoleService extends BaseService<SysRole, SysRoleMapper> {
	@Autowired
	private SysDeptService sysDeptService;
	@Autowired
	private SysAuthorizeService sysAuthorizeService;

	public SysRole queryById(Long id) {
		SysRole sysRole = super.queryById(id);
		if (sysRole != null) {
			if (sysRole.getDeptId() != null) {
				SysDept sysDept = sysDeptService.queryById(sysRole.getDeptId());
				if (sysDept != null) {
					sysRole.setDeptName(sysDept.getDeptName());
				} else {
					sysRole.setDeptId(null);
				}
			}
		}
		return sysRole;
	}

	public Pagination<SysRole> query(Map<String, Object> params) {
	    Pagination<SysRole> pageInfo = super.query(params);
		// 权限信息
		for (SysRole bean : pageInfo.getRecords()) {
			if (bean.getDeptId() != null) {
				SysDept sysDept = sysDeptService.queryById(bean.getDeptId());
				if (sysDept != null) {
					bean.setDeptName(sysDept.getDeptName());
				}
			}
			List<String> permissions = sysAuthorizeService.queryRolePermission(bean.getId());
			for (String permission : permissions) {
				if (StringUtils.isBlank(bean.getPermission())) {
					bean.setPermission(permission);
				} else {
					bean.setPermission(bean.getPermission() + ";" + permission);
				}
			}
		}
		return pageInfo;
	}
}
