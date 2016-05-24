package org.ibase4j.mybatis.sys.dao;

import java.util.Map;

import org.ibase4j.mybatis.generator.model.SysSession;

import com.github.pagehelper.Page;

public interface SysSessionExpandMapper {

	void deleteBySessionId(String sessionId);

	Page<Integer> query(Map<String, Object> params);

	SysSession queryBySessionId(String sessionId);

}
