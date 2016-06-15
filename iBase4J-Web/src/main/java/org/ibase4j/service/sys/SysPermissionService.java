package org.ibase4j.service.sys;

import org.ibase4j.core.base.BaseService;
import org.ibase4j.model.generator.SysPermission;
import org.ibase4j.provider.sys.SysPermissionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:16:13
 */
@Service
public class SysPermissionService extends BaseService<SysPermissionProvider, SysPermission> {
	@Autowired
	public void setProvider(SysPermissionProvider provider) {
		this.provider = provider;
	}

	@Cacheable("sysPermission")
	public Boolean doCheckPermissionByUserId(Integer userId, String url) {
		return provider.doCheckPermissionByUserId(userId, url);
	}
}
