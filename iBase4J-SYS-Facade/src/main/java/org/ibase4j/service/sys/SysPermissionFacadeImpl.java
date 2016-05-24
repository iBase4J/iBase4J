package org.ibase4j.service.sys;

import org.ibase4j.core.support.dubbo.BaseFacadeImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.facade.sys.SysPermissionFacade;
import org.ibase4j.mybatis.generator.dao.SysPermissionMapper;
import org.ibase4j.mybatis.generator.model.SysPermission;
import org.ibase4j.mybatis.sys.dao.SysPermissionExpandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

/**
 * URL权限管理
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:49
 */
@DubboService(interfaceClass = SysPermissionFacade.class)
@CacheConfig(cacheNames = "sysPermission")
public class SysPermissionFacadeImpl extends BaseFacadeImpl<SysPermission> implements SysPermissionFacade {
	@Autowired
	private SysPermissionMapper sysPermissionMapper;
	@Autowired
	private SysPermissionExpandMapper sysPermissionExpandMapper;

	@Cacheable
	public boolean doCheckPermissionByUserId(Integer userId, String url) {
		return sysPermissionExpandMapper.getPermissionByUserId(userId, url) > 0;
	}

	@Cacheable
	public SysPermission queryById(Integer id) {
		return sysPermissionMapper.selectByPrimaryKey(id);
	}
}
