package org.ibase4j.dao.scheduler;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.ibase4j.core.base.BaseMapper;
import org.ibase4j.model.scheduler.TaskScheduler;

public interface TaskSchedulerMapper extends BaseMapper<TaskScheduler> {

    List<String> selectIdByMap(@Param("cm") Map<String, Object> params);
}