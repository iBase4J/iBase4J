/**
 * 
 */
package org.ibase4j.service.sys;

import java.util.Map;

import org.ibase4j.mybatis.generator.dao.SysUserMapper;
import org.ibase4j.mybatis.generator.model.SysUser;
import org.ibase4j.mybatis.sys.dao.SysUserExpandMapper;
import org.ibase4j.service.BaseService;
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
@CacheConfig(cacheNames = "sysUser")
public class SysUserService extends BaseService {
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysUserExpandMapper sysUserExpandMapper;

	@CacheEvict
	@Transactional
	public void add(SysUser record) {
		record.setUsable(1);
		sysUserMapper.insert(record);
	}

	@CacheEvict
	@Transactional
	public void update(SysUser record) {
		sysUserMapper.updateByPrimaryKey(record);
	}

	@CacheEvict
	@Transactional
	public void delete(Integer id) {
		sysUserMapper.deleteByPrimaryKey(id);
	}

	@Cacheable
	public SysUser queryById(Integer id) {
		return sysUserMapper.selectByPrimaryKey(id);
	}

	@Cacheable
	public PageInfo<SysUser> query(Map<String, Object> params) {
		this.startPage(params);
		Page<SysUser> list = sysUserExpandMapper.query(params);
		return new PageInfo<SysUser>(list);
	}
}
