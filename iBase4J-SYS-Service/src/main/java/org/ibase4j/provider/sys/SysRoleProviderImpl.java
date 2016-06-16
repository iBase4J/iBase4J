package org.ibase4j.provider.sys;

import java.util.Map;

import org.ibase4j.core.base.BaseMapper;
import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.dao.generator.SysRoleMapper;
import org.ibase4j.dao.sys.SysRoleExpandMapper;
import org.ibase4j.model.generator.SysRole;
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
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysRoleExpandMapper sysRoleExpandMapper;

	@SuppressWarnings("unchecked")
	protected BaseMapper<SysRole> getMapper() {
		return sysRoleMapper;
	}

	public PageInfo<SysRole> query(Map<String, Object> params) {
		startPage(params);
		return getPage(sysRoleExpandMapper.query(params));
	}
}
