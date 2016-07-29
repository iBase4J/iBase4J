package org.ibase4j.dao.sys;

import java.util.List;

import org.ibase4j.core.base.BaseExpandMapper;

public interface SysSessionExpandMapper extends BaseExpandMapper {

	void deleteBySessionId(String sessionId);

	String queryBySessionId(String sessionId);

	List<String> querySessionIdByAccount(String account);

}
