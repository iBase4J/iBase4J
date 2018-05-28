package org.ibase4j.service;

import java.util.Map;

import org.ibase4j.model.SysParam;

import top.ibase4j.core.base.BaseService;

/**
 * @author ShenHuaJie
 * @since 2018年4月24日 上午10:01:26
 */
public interface SysParamService extends BaseService<SysParam> {
    Map<String, String> getAllParams();

    String getName(String key);

    String getValue(String key);

    String getValue(String key, String defaultValue);
}
