package org.ibase4j.mapper;

import java.util.List;

import org.ibase4j.model.SysSession;

import top.ibase4j.core.base.BaseMapper;

public interface SysSessionMapper extends BaseMapper<SysSession> {

    void deleteBySessionId(String sessionId);

    Long queryBySessionId(String sessionId);

    List<String> querySessionIdByAccount(String account);
}