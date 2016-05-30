package org.ibase4j.mybatis.scheduler.dao;

import java.util.List;
import java.util.Map;

public interface TaskSchedulerExpandMapper {

	List<Integer> query(Map<String, Object> params);

}
