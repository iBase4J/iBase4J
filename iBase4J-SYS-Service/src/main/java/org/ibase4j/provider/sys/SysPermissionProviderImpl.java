package org.ibase4j.provider.sys;

import java.util.Map;

import org.ibase4j.core.base.BaseMapper;
import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.dao.generator.SysPermissionMapper;
import org.ibase4j.dao.sys.SysPermissionExpandMapper;
import org.ibase4j.model.generator.SysPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import com.github.pagehelper.PageInfo;

/**
 * URL权限管理
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:49
 */
@CacheConfig(cacheNames = "sysPermission")
@DubboService(interfaceClass = SysPermissionProvider.class)
public class SysPermissionProviderImpl extends BaseProviderImpl<SysPermission> implements SysPermissionProvider {
	@Autowired
	private SysPermissionMapper sysPermissionMapper;
	@Autowired
	private SysPermissionExpandMapper sysPermissionExpandMapper;

	@SuppressWarnings("unchecked")
	protected BaseMapper<SysPermission> getMapper() {
		return sysPermissionMapper;
	}

	@Cacheable
	public boolean doCheckPermissionByUserId(Integer userId, String url) {
		return sysPermissionExpandMapper.getPermissionByUserId(userId, url) > 0;
	}

	public PageInfo<SysPermission> query(Map<String, Object> params) {
		startPage(params);
		return getPage(sysPermissionExpandMapper.query(params));
	}
}
