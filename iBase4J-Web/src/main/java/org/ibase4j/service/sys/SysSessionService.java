package org.ibase4j.service.sys;

import java.util.Map;

import org.ibase4j.core.config.Resources;
import org.ibase4j.facade.sys.SysSessionFacade;
import org.ibase4j.mybatis.generator.model.SysSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.github.pagehelper.PageInfo;

/**
 * 会话管理
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:08:32
 */
@Service
public class SysSessionService {
	@Autowired
	private SysSessionFacade sysSessionFacade;
	@Autowired
	private RedisOperationsSessionRepository sessionRepository;

	public PageInfo<?> query(Map<String, Object> params) {
		return sysSessionFacade.query(params);
	}

	/** 删除会话 */
	public void delete(Integer id) {
		Assert.notNull(id, Resources.getMessage("ID_IS_NULL"));
		SysSession sysSession = sysSessionFacade.queryById(id);
		if (sysSession != null) {
			sessionRepository.delete(sysSession.getSessionId());
			sessionRepository.cleanupExpiredSessions();
			sysSessionFacade.delete(id);
		}
	}

	/** 更新会话 */
	public void update(SysSession record) {
		sysSessionFacade.update(record);
	}

	/** 删除会话 */
	public void deleteBySessionId(String sessionId) {
		sysSessionFacade.deleteBySessionId(sessionId);
	}

}
