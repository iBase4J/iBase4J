package org.ibase4j.service;

import java.util.List;
import java.util.Map;

import org.ibase4j.model.SysDic;

import top.ibase4j.core.base.BaseService;

/**
 * @author ShenHuaJie
 * @since 2018年4月24日 上午10:59:25
 */
public interface SysDicService extends BaseService<SysDic> {
    Map<String, Map<String, String>> getAllDic();

    Map<String, String> queryDicByTypeMap(Map<String, Object> params);

    Map<String, String> queryDicByType(String key);

    String getText(String parentType, String parentCode, String type, String code);

    List<SysDic> getDics(String parentType, String parentCode, String type);
}
