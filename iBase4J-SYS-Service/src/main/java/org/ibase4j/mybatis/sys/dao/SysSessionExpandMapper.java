package org.ibase4j.mybatis.sys.dao;

import org.ibase4j.core.support.BaseMapper;

public interface SysSessionExpandMapper extends BaseMapper {

	void deleteBySessionId(String sessionId);

	Integer queryBySessionId(String sessionId);

}
