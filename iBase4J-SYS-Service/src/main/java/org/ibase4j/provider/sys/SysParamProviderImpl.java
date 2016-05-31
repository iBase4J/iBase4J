package org.ibase4j.provider.sys;

import java.util.Map;

import org.ibase4j.core.support.dubbo.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.mybatis.generator.dao.SysParamMapper;
import org.ibase4j.mybatis.generator.model.SysParam;
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
@DubboService
@CacheConfig(cacheNames = "sysParam")
public class SysParamProviderImpl extends BaseProviderImpl<SysParam> implements SysParamProvider {
	@Autowired
	private SysParamMapper sysParamMapper;

	@CachePut
	public SysParam update(SysParam record) {
		if (record.getId() == null) {
			sysParamMapper.insert(record);
		} else {
			sysParamMapper.updateByPrimaryKey(record);
		}
		return record;
	}

	@CacheEvict
	public void delete(Integer id) {
		sysParamMapper.deleteByPrimaryKey(id);
	}

	public PageInfo<SysParam> query(Map<String, Object> params) {
		return null;
	}

	@Cacheable
	public SysParam queryById(Integer id) {
		return sysParamMapper.selectByPrimaryKey(id);
	}

}
