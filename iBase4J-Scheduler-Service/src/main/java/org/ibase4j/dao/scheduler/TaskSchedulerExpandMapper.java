package org.ibase4j.dao.scheduler;

import java.util.Map;

import com.github.pagehelper.Page;

public interface TaskSchedulerExpandMapper {
	Page<String> queryGroup(Map<String, Object> params);

	Page<String> queryScheduler(Map<String, Object> params);

	Page<String> queryLog(Map<String, Object> params);

}
