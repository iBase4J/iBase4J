package org.ibase4j.service.sys;

import java.util.Map;

import org.ibase4j.model.sys.SysDic;

import top.ibase4j.core.base.BaseService;

/**
 * @author ShenHuaJie
 * @since 2018年4月24日 上午10:59:25
 */
public interface SysDicService extends BaseService<SysDic> {
    Map<String, Map<String, String>> getAllDic();

    Map<String, String> queryDicByTypeMap(Map<String, Object> params);

    Map<String, String> queryDicByType(String key);
}
