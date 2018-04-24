package org.ibase4j.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.ibase4j.mapper.SysParamMapper;
import org.ibase4j.model.SysParam;
import org.ibase4j.service.ISysParamService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import com.alibaba.dubbo.config.annotation.Service;
import com.weibo.api.motan.config.springsupport.annotation.MotanService;

import top.ibase4j.core.Constants;
import top.ibase4j.core.base.BaseService;
import top.ibase4j.core.support.context.ApplicationContextHolder;
import top.ibase4j.core.util.InstanceUtil;

/**
 * @author ShenHuaJie
 * @version 2016年5月31日 上午11:01:33
 */
@CacheConfig(cacheNames = "sysParam")
@Service(interfaceClass = ISysParamService.class)
@MotanService(interfaceClass = ISysParamService.class)
public class SysParamServiceImpl extends BaseService<SysParam, SysParamMapper> implements ISysParamService {
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

    public String getValue(String key) {
        return getValue(key, "");
    }

    public String getValue(String key, String defaultValue) {
        String value = ApplicationContextHolder.getBean(ISysParamService.class).getAllParams().get(key);
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        return value;
    }
}
