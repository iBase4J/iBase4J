package org.ibase4j.mybatis.sys.dao;

import java.util.List;

public interface SysDicExpandMapper {

	List<Integer> queryDicByDicIndexKey(String key);

}
