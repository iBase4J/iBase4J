package org.ibase4j.mybatis.sys.dao;

import java.util.List;

import org.ibase4j.mybatis.generator.model.SysDic;

public interface SysDicExpandMapper {

	List<SysDic> queryDicByDicIndexKey(String key);

}
