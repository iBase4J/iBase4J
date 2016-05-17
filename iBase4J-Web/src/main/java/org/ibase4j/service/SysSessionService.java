package org.ibase4j.service;

import java.util.Map;

import org.ibase4j.core.config.Resources;
import org.ibase4j.facade.sys.SysSessionFacade;
import org.ibase4j.mybatis.generator.model.SysSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.github.pagehelper.PageInfo;

@Service
public class SysSessionService {
	@Autowired
	private SysSessionFacade sysSessionFacade;

	public PageInfo<?> query(Map<String, Object> params) {
		return sysSessionFacade.query(params);
	}

	public void delete(Integer id) {
		Assert.notNull(id, Resources.getMessage("ID_IS_NULL"));
		sysSessionFacade.delete(id);
	}

	public void update(SysSession record) {
		sysSessionFacade.update(record);
	}

	public void deleteBySessionId(String sessionId) {
		sysSessionFacade.deleteBySessionId(sessionId);
	}

}
