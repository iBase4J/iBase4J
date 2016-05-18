package org.ibase4j.service.sys;

import java.util.Map;

import org.ibase4j.core.config.Resources;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.facade.sys.SysDeptFacade;
import org.ibase4j.mybatis.generator.dao.SysDeptMapper;
import org.ibase4j.mybatis.generator.model.SysDept;
import org.ibase4j.mybatis.sys.dao.SysDeptExpandMapper;
import org.ibase4j.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

@CacheConfig(cacheNames = "sysDept")
@DubboService(interfaceClass = SysDeptFacade.class)
public class SysDeptService extends BaseService implements SysDeptFacade {
	@Autowired
	private SysDeptMapper sysDeptMapper;
	@Autowired
	private SysDeptExpandMapper syDeptExpandMapper;

	@CachePut
	@Transactional
	public void update(SysDept record) {
		if (record.getId() == null) {
			record.setEnable(1);
			sysDeptMapper.insert(record);
		} else {
			sysDeptMapper.updateByPrimaryKey(record);
		}
	}

	@CacheEvict
	@Transactional
	public void delete(Integer id) {
		SysDept record = queryById(id);
		Assert.notNull(record, String.format(Resources.getMessage("USER_IS_NULL"), id));
		record.setEnable(0);
		update(record);
	}

	@Cacheable
	public SysDept queryById(Integer id) {
		return sysDeptMapper.selectByPrimaryKey(id);
	}

	@Cacheable
	public PageInfo<SysDept> query(Map<String, Object> params) {
		this.startPage(params);
		Page<SysDept> list = syDeptExpandMapper.query(params);
		return new PageInfo<SysDept>(list);
	}
}
