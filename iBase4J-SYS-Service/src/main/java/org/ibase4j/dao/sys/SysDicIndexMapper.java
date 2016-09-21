package org.ibase4j.dao.sys;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.ibase4j.core.base.BaseMapper;
import org.ibase4j.model.sys.SysDicIndex;

public interface SysDicIndexMapper extends BaseMapper<SysDicIndex> {
    List<String> selectIdByMap(@Param("cm") Map<String, Object> params);
}
