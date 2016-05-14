package org.ibase4j.service.sys;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionKey;
import org.ibase4j.mybatis.generator.dao.SysSessionMapper;
import org.ibase4j.mybatis.generator.model.SysSession;
import org.ibase4j.mybatis.sys.dao.SysSessionExpandMapper;
import org.ibase4j.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

@Service
@CacheConfig(cacheNames = "sysSession")
public class SysSessionService extends BaseService {
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
		SysSession sysSession = sessionMapper.selectByPrimaryKey(id);
		if (sysSession != null) {
			SessionKey sessionKey = new DefaultSessionKey(sysSession.getSessionId());
			Session session = SecurityUtils.getSecurityManager().getSession(sessionKey);
			if (session != null) {
				session.stop();
			}
		}
		sessionMapper.deleteByPrimaryKey(id);
	}

	@CacheEvict
	public void deleteBySessionId(final String sessionId) {
		sessionExpandMapper.deleteBySessionId(sessionId);
	}

	@Cacheable
	public PageInfo<SysSession> query(Map<String, Object> params) {
		this.startPage(params);
		Page<SysSession> list = sessionExpandMapper.query(params);
		return new PageInfo<SysSession>(list);
	}
}
