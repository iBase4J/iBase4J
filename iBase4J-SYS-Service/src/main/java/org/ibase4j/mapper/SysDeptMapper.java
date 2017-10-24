package org.ibase4j.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.ibase4j.model.SysDept;

import top.ibase4j.core.base.BaseMapper;

public interface SysDeptMapper extends BaseMapper<SysDept> {

	List<Long> selectIdPage(@Param("cm") SysDept sysDept);
}