package org.ibase4j.provider.sys;

import java.util.Map;

import org.ibase4j.core.support.dubbo.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.mybatis.generator.dao.SysRoleMapper;
import org.ibase4j.mybatis.generator.model.SysRole;
import org.ibase4j.mybatis.sys.dao.SysRoleExpandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

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

	@CachePut
	public SysRole update(SysRole record) {
		if (record.getId() == null) {
			sysRoleMapper.insert(record);
		} else {
			sysRoleMapper.updateByPrimaryKey(record);
		}
		return record;
	}

	@CacheEvict
	public void delete(Integer id) {
		sysRoleMapper.deleteByPrimaryKey(id);
	}

	public PageInfo<SysRole> query(Map<String, Object> params) {
		startPage(params);
		return getPage(sysRoleExpandMapper.query(params));
	}

	@Cacheable
	public SysRole queryById(Integer id) {
		return sysRoleMapper.selectByPrimaryKey(id);
	}

}
