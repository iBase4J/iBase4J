package org.ibase4j.dao.sys;

import java.util.Map;

import com.github.pagehelper.Page;

public interface SysDicExpandMapper {

	Page<Integer> queryDicIndex(Map<String, Object> params);

	Page<Integer> queryDic(Map<String, Object> params);

}
