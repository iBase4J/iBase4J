package org.ibase4j.core.support;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.plugins.Page;

public interface SysEventService {

	void saveEvent(HttpServletRequest request, HttpServletResponse response, Exception ex, Long startTime,
			Long endTime);

	public Page<Map<String, Object>> queryMap(Map<String, Object> params);
}
