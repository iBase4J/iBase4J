package org.ibase4j.service;

import java.util.Map;

import org.ibase4j.core.util.DataUtil;

import com.github.pagehelper.PageHelper;

/** 业务逻辑层基类 */
public class BaseService {
	/** 启动分页查询 */
	protected void startPage(Map<String, Object> params) {
		if (DataUtil.isEmpty(params.get("pageNum"))) {
			params.put("pageNum", 1);
		}
		if (DataUtil.isEmpty(params.get("pageSize"))) {
			params.put("pageSize", 10);
		}
		if (DataUtil.isEmpty(params.get("orderBy"))) {
			params.put("orderBy", "id_ desc");
		}
		PageHelper.startPage(params);
	}
}
