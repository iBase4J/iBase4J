package org.ibase4j.mapper.sys;

import java.util.List;

import org.ibase4j.core.base.BaseMapper;
import org.ibase4j.model.sys.SysSession;

public interface SysSessionMapper extends BaseMapper<SysSession> {

    void deleteBySessionId(String sessionId);

    Long queryBySessionId(String sessionId);

    List<String> querySessionIdByAccount(String account);
}