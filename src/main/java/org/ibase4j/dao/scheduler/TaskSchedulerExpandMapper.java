package org.ibase4j.dao.scheduler;

import java.util.Map;

import com.github.pagehelper.Page;

public interface TaskSchedulerExpandMapper {
	Page<Integer> queryGroup(Map<String, Object> params);

	Page<Integer> queryScheduler(Map<String, Object> params);

	Page<Integer> queryLog(Map<String, Object> params);

}
