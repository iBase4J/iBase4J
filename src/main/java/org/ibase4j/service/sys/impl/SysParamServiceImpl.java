package org.ibase4j.service.sys.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.ibase4j.mapper.sys.SysParamMapper;
import org.ibase4j.model.sys.SysParam;
import org.ibase4j.service.sys.ISysParamService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import top.ibase4j.core.Constants;
import top.ibase4j.core.base.BaseService;
import top.ibase4j.core.support.context.ApplicationContextHolder;
import top.ibase4j.core.util.InstanceUtil;

/**
 * @author ShenHuaJie
 * @version 2016年5月31日 上午11:01:33
 */
@Service
@CacheConfig(cacheNames = "sysParam")
public class SysParamServiceImpl extends BaseService<SysParam, SysParamMapper> implements ISysParamService {
    @Override
    @Cacheable(value = Constants.CACHE_NAMESPACE + "sysParams")
    public Map<String, String> getAllParams() {
        Map<String, Object> params = InstanceUtil.newHashMap();
        params.put("orderBy", "type_,sort_no");
        List<SysParam> list = queryList(params);
        Map<String, String> resultMap = InstanceUtil.newHashMap();
        for (SysParam sysParam : list) {
            if (sysParam != null) {
                resultMap.put(sysParam.getParamKey(), sysParam.getParamValue());
            }
        }
        return resultMap;
    }

    @Override
    @Cacheable(value = Constants.CACHE_NAMESPACE + "sysParamName")
    public String getName(String key) {
        if (StringUtils.isBlank(key)) {
            return "";
        }
        Map<String, Object> params = InstanceUtil.newHashMap();
        params.put("orderBy", "type_,sort_no");
        List<SysParam> list = queryList(params);
        for (SysParam sysParam : list) {
            if (sysParam != null) {
                if (key.equals(sysParam.getParamKey())) {
                    return sysParam.getRemark();
                }
            }
        }
        return "";
    }

    @Override
    public String getValue(String key) {
        return getValue(key, "");
    }

    @Override
    public String getValue(String key, String defaultValue) {
        String value = ApplicationContextHolder.getBean(ISysParamService.class).getAllParams().get(key);
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        return value;
    }
}
