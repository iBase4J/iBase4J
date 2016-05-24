package org.ibase4j.service.sys;

import java.util.Map;

import org.ibase4j.core.support.dubbo.BaseFacadeImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.facade.sys.SysSessionFacade;
import org.ibase4j.mybatis.generator.dao.SysSessionMapper;
import org.ibase4j.mybatis.generator.model.SysSession;
import org.ibase4j.mybatis.sys.dao.SysSessionExpandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.github.pagehelper.PageInfo;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@CacheConfig(cacheNames = "sysSession")
@DubboService(interfaceClass = SysSessionFacade.class)
public class SysSessionFacadeImpl extends BaseFacadeImpl<SysSession> implements SysSessionFacade {
	@Autowired
	private SysSessionMapper sessionMapper;
	@Autowired
	private SysSessionExpandMapper sessionExpandMapper;

	@CachePut
	public SysSession update(SysSession record) {
		if (record.getId() == null) {
			Integer id = sessionExpandMapper.queryBySessionId(record.getSessionId());
			if (id != null) {
				record.setId(id);
				sessionMapper.updateByPrimaryKey(record);
			} else {
				sessionMapper.insert(record);
			}
		} else {
			sessionMapper.updateByPrimaryKey(record);
		}
		return record;
	}

	@CacheEvict
	public void delete(Integer id) {
		sessionMapper.deleteByPrimaryKey(id);
	}

	// 系统触发,由系统自动管理缓存
	public void deleteBySessionId(final String sessionId) {
		sessionExpandMapper.deleteBySessionId(sessionId);
	}

	@Cacheable
	public SysSession queryById(Integer id) {
		return sessionMapper.selectByPrimaryKey(id);
	}

	public PageInfo<SysSession> query(Map<String, Object> params) {
		this.startPage(params);
		return getPage(sessionExpandMapper.query(params));
	}
}
