package org.ibase4j.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.ibase4j.mapper.SysRoleMenuMapper;
import org.ibase4j.model.SysDept;
import org.ibase4j.model.SysRole;
import org.ibase4j.service.ISysDeptService;
import org.ibase4j.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.plugins.Page;

import top.ibase4j.core.base.BaseService;

/**
 * @author ShenHuaJie
 * @version 2016年5月31日 上午11:01:33
 */
@CacheConfig(cacheNames = "sysRole")
@Service(interfaceClass = ISysRoleService.class)
public class SysRoleServiceImpl extends BaseService<SysRole> implements ISysRoleService {
	@Autowired
	private ISysDeptService sysDeptService;
	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;

	public Page<SysRole> query(Map<String, Object> params) {
		Page<SysRole> pageInfo = super.query(params);
		// 权限信息
		for (SysRole bean : pageInfo.getRecords()) {
			if (bean.getDeptId() != null) {
				SysDept sysDept = sysDeptService.queryById(bean.getDeptId());
				bean.setDeptName(sysDept.getDeptName());
			}
			List<String> permissions = sysRoleMenuMapper.queryPermission(bean.getId());
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
