package org.ibase4j.core.support;

import java.util.Map;

import org.ibase4j.model.SysEvent;

import com.baomidou.mybatisplus.plugins.Page;

public interface ISysEventService {
	public void update(SysEvent sysEvent);

	public Page<?> queryMap(Map<String, Object> params);
}
