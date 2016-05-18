package org.ibase4j.mybatis.sys.dao;

import java.util.Map;

import org.ibase4j.mybatis.generator.model.SysDept;

import com.github.pagehelper.Page;

public interface SysDeptExpandMapper {

	Page<SysDept> query(Map<String, Object> params);

}
