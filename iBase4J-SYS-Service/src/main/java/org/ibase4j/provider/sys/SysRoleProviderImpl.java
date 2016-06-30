package org.ibase4j.provider.sys;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.dao.sys.SysRoleExpandMapper;
import org.ibase4j.model.generator.SysRole;
import org.ibase4j.model.sys.SysRoleBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;

import com.github.pagehelper.PageInfo;

/**
 * @author ShenHuaJie
 * @version 2016年5月31日 上午11:01:33
 */
@CacheConfig(cacheNames = "sysRole")
@DubboService(interfaceClass = SysRoleProvider.class)
public class SysRoleProviderImpl extends BaseProviderImpl<SysRole> implements SysRoleProvider {
	@Autowired
	private SysRoleExpandMapper sysRoleExpandMapper;

	public PageInfo<SysRole> query(Map<String, Object> params) {
		startPage(params);
		return getPage(sysRoleExpandMapper.query(params));
	}

	public PageInfo<SysRoleBean> queryBean(Map<String, Object> params) {
		startPage(params);
		PageInfo<SysRoleBean> pageInfo = getPage(sysRoleExpandMapper.query(params), SysRoleBean.class);
		// 权限信息
		for (SysRoleBean bean : pageInfo.getList()) {
			List<String> permissions = sysRoleExpandMapper.queryPermission(bean.getId());
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
