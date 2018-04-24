package org.ibase4j.service;

import java.util.Map;

import org.ibase4j.model.SysDic;

import top.ibase4j.core.base.IBaseService;

/**
 * @author ShenHuaJie
 * @since 2018年4月24日 上午10:59:25
 */
public interface ISysDicService extends IBaseService<SysDic> {
    public Map<String, Map<String, String>> getAllDic();

    public Map<String, String> queryDicByTypeMap(Map<String, Object> params);

    public Map<String, String> queryDicByType(String key);
}
