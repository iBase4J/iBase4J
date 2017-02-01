package org.ibase4j.provider.sys;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.dao.sys.SysRoleMenuMapper;
import org.ibase4j.model.sys.SysRole;
import org.ibase4j.model.sys.ext.SysRoleBean;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * @author ShenHuaJie
 * @version 2016年5月31日 上午11:01:33
 */
@CacheConfig(cacheNames = "sysRole")
@DubboService(interfaceClass = ISysRoleProvider.class)
public class SysRoleProviderImpl extends BaseProviderImpl<SysRole> implements ISysRoleProvider {
	@Autowired
	private ISysDeptProvider sysDeptProvider;
	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;

	public Page<SysRoleBean> queryBean(Map<String, Object> params) {
		Page<SysRoleBean> pageInfo = query(params, SysRoleBean.class);
		// 权限信息
		for (SysRoleBean bean : pageInfo.getRecords()) {
			if (bean.getDeptId() != null) {
				bean.setDeptName(sysDeptProvider.queryById(bean.getDeptId()).getDeptName());
			}
			List<String> permissions = sysRoleMenuMapper.queryPermission(bean.getId());
			int i = 0;
			for (String permission : permissions) {
				if (StringUtils.isBlank(bean.getPermission())) {
					bean.setPermission(permission);
				} else {
					bean.setPermission(bean.getPermission() + ";");
					if (i % 2 == 0) {
						bean.setPermission(bean.getPermission() + "<br/>");
					}
					bean.setPermission(bean.getPermission() + permission);
				}
				i++;
			}
		}
		return pageInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ibase4j.provider.SysRoleProvider#getPermissions(java.lang.String)
	 */
	@Override
	public List<String> getPermissions(Long id) {
		return sysRoleMenuMapper.getPermissions(id);
	}
}
