package org.ibase4j.service;

import java.util.Map;

import org.ibase4j.model.SysParam;

import top.ibase4j.core.base.IBaseService;

/**
 * @author ShenHuaJie
 * @since 2018年2月26日 下午7:50:19
 */
public interface ISysParamService extends IBaseService<SysParam> {
	public Map<String, String> getAllParams();

	public String getName(String key);

	public String getValue(String key);

	public String getValue(String key, String defaultValue);
}
