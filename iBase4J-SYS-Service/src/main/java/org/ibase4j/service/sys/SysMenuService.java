package org.ibase4j.service.sys;

import java.util.Map;

import org.ibase4j.core.support.BaseService;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.facade.sys.SysMenuFacade;
import org.ibase4j.mybatis.generator.dao.SysMenuMapper;
import org.ibase4j.mybatis.generator.model.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.github.pagehelper.PageInfo;

@CacheConfig(cacheNames = "sysMenu")
@DubboService(interfaceClass = SysMenuFacade.class)
public class SysMenuService extends BaseService<SysMenu> implements SysMenuFacade {
	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Cacheable
	public SysMenu queryById(Integer id) {
		return sysMenuMapper.selectByPrimaryKey(id);
	}

	@CachePut
	public void update(SysMenu record) {
		if (record.getId() == null) {
			sysMenuMapper.insert(record);
		} else {
			sysMenuMapper.updateByPrimaryKey(record);
		}
	}

	@CacheEvict
	public void delete(Integer id) {
		sysMenuMapper.deleteByPrimaryKey(id);
	}

	public PageInfo<SysMenu> query(Map<String, Object> params) {
		return null;
	}

}
