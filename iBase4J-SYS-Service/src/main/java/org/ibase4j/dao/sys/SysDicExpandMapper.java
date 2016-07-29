package org.ibase4j.dao.sys;

import java.util.Map;

import com.github.pagehelper.Page;

public interface SysDicExpandMapper {

	Page<String> queryDicIndex(Map<String, Object> params);

	Page<String> queryDic(Map<String, Object> params);

}
