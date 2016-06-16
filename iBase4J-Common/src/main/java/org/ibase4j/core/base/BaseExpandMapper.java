package org.ibase4j.core.base;

import java.util.Map;

import com.github.pagehelper.Page;

/**
 * @author ShenHuaJie
 * @version 2016年6月3日 下午2:30:14
 */
public interface BaseExpandMapper {
	/** 条件分页查询 */
	Page<Integer> query(Map<String, Object> params);
}
