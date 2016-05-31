package org.ibase4j.core.support;

import java.util.Map;

import com.github.pagehelper.Page;

public interface BaseMapper {
	Page<Integer> query(Map<String, Object> params);
}
