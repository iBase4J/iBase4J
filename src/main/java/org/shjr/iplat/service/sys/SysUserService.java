/**
 * 
 */
package org.shjr.iplat.service.sys;

import java.util.Map;

import org.shjr.iplat.mybatis.generator.dao.SysUserMapper;
import org.shjr.iplat.mybatis.generator.model.SysUser;
import org.shjr.iplat.mybatis.sys.dao.SysUserExpandMapper;
import org.shjr.iplat.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

/**
 * @author ShenHuaJie
 */
@Service
@CacheConfig(cacheNames = { "queryById", "query" })
public class SysUserService extends BaseService {
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysUserExpandMapper sysUserExpandMapper;

	@Transactional
	@CacheEvict(allEntries = true)
	public void add(SysUser record) {
		record.setUsable(1);
		sysUserMapper.insert(record);
	}

	@Transactional
	@CacheEvict(allEntries = true)
	public void update(SysUser record) {
		sysUserMapper.updateByPrimaryKey(record);
	}

	@Transactional
	@CacheEvict(allEntries = true)
	public void delete(Integer id) {
		sysUserMapper.deleteByPrimaryKey(id);
	}

	@Cacheable("queryById")
	public SysUser queryById(Integer id) {
		return sysUserMapper.selectByPrimaryKey(id);
	}

	@Cacheable("query")
	public PageInfo<Map<String, Object>> query(Map<String, Object> params) {
		this.startPage(params);
		Page<Map<String, Object>> list = sysUserExpandMapper.query(params);
		return new PageInfo<Map<String, Object>>(list);
	}
}
