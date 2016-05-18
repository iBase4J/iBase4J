package org.ibase4j.service;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionKey;
import org.ibase4j.core.config.Resources;
import org.ibase4j.facade.sys.SysSessionFacade;
import org.ibase4j.mybatis.generator.model.SysSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.github.pagehelper.PageInfo;

@Service
public class SysSessionService {
	private final Logger logger = LogManager.getLogger();
	@Autowired
	private SysSessionFacade sysSessionFacade;

	public PageInfo<?> query(Map<String, Object> params) {
		return sysSessionFacade.query(params);
	}

	public void delete(Integer id) {
		Assert.notNull(id, Resources.getMessage("ID_IS_NULL"));
		SysSession sysSession = sysSessionFacade.queryById(id);
		if (sysSession != null) {
			try {
				SessionKey sessionKey = new DefaultSessionKey(sysSession.getSessionId());
				Session session = SecurityUtils.getSecurityManager().getSession(sessionKey);
				if (session != null) {
					session.stop();
				}
			} catch (Exception e) {
				logger.error(e);
			}
		}
		sysSessionFacade.delete(id);
	}

	public void update(SysSession record) {
		sysSessionFacade.update(record);
	}

	public void deleteBySessionId(String sessionId) {
		sysSessionFacade.deleteBySessionId(sessionId);
	}

}
