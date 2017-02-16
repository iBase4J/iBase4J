package org.ibase4j.mapper.scheduler;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.ibase4j.model.scheduler.TaskFireLog;

import com.baomidou.mybatisplus.mapper.BaseMapper;

public interface TaskFireLogMapper extends BaseMapper<TaskFireLog> {
    List<Long> selectIdByMap(RowBounds rowBounds, @Param("cm") Map<String, Object> params);
}
