package org.ibase4j.service.sys;

import org.ibase4j.facade.sys.SysPermissionFacade;
import org.ibase4j.mybatis.sys.dao.SysPermissionExpandMapper;
import org.ibase4j.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * URL权限管理
 * 
 * @author ShenHuaJie
 */
@Service
@CacheConfig(cacheNames = "sysPermission")
public class SysPermissionService extends BaseService implements SysPermissionFacade {

	@Autowired
	private SysPermissionExpandMapper sysPermissionExpandMapper;

	@Cacheable
	public boolean doCheckPermissionByUserId(Integer userId, String url) {
		return sysPermissionExpandMapper.getPermissionByUserId(userId, url) > 0;
	}
}
