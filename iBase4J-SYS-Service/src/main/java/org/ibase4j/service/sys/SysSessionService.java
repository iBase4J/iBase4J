package org.ibase4j.service.sys;

import java.util.Map;

import org.ibase4j.core.support.BaseService;
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

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

@CacheConfig(cacheNames = "sysSession")
@DubboService(interfaceClass = SysSessionFacade.class)
public class SysSessionService extends BaseService<SysSession> implements SysSessionFacade {
	@Autowired
	private SysSessionMapper sessionMapper;
	@Autowired
	private SysSessionExpandMapper sessionExpandMapper;

	@CachePut
	public void update(SysSession record) {
		if (record.getId() == null) {
			SysSession session = sessionExpandMapper.queryBySessionId(record.getSessionId());
			if (session != null) {
				record.setId(session.getId());
				sessionMapper.updateByPrimaryKey(record);
			} else {
				sessionMapper.insert(record);
			}
		} else {
			sessionMapper.updateByPrimaryKey(record);
		}
	}

	@CacheEvict
	public void delete(Integer id) {
		sessionMapper.deleteByPrimaryKey(id);
	}

	@CacheEvict
	public void deleteBySessionId(final String sessionId) {
		sessionExpandMapper.deleteBySessionId(sessionId);
	}

	@Cacheable
	public SysSession queryById(Integer id) {
		return sessionMapper.selectByPrimaryKey(id);
	}

	public PageInfo<SysSession> query(Map<String, Object> params) {
		this.startPage(params);
		Page<Integer> ids = sessionExpandMapper.query(params);
		return new PageInfo<SysSession>(getPage(ids));
	}
}
