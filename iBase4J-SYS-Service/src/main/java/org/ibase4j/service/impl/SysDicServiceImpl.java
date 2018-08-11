package org.ibase4j.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.ibase4j.mapper.SysDicMapper;
import org.ibase4j.model.SysDic;
import org.ibase4j.service.SysDicService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import com.alibaba.dubbo.config.annotation.Service;
import com.weibo.api.motan.config.springsupport.annotation.MotanService;

import top.ibase4j.core.Constants;
import top.ibase4j.core.base.BaseServiceImpl;
import top.ibase4j.core.util.InstanceUtil;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@CacheConfig(cacheNames = "sysDic")
@Service(interfaceClass = SysDicService.class)
@MotanService(interfaceClass = SysDicService.class)
public class SysDicServiceImpl extends BaseServiceImpl<SysDic, SysDicMapper> implements SysDicService {

    @Override
    @Cacheable(value = Constants.CACHE_NAMESPACE + "sysDics")
    public Map<String, Map<String, String>> getAllDic() {
        Map<String, Object> params = InstanceUtil.newHashMap();
        params.put("orderBy", "type_,sort_no");
        List<SysDic> list = queryList(params);
        Map<String, Map<String, String>> resultMap = InstanceUtil.newHashMap();
        for (SysDic sysDic : list) {
            if (sysDic != null) {
                String key = sysDic.getType();
                if (resultMap.get(key) == null) {
                    Map<String, String> dicMap = InstanceUtil.newHashMap();
                    resultMap.put(key, dicMap);
                }
                if (StringUtils.isNotBlank(sysDic.getParentCode())) {
                    resultMap.get(key).put(sysDic.getParentCode() + sysDic.getCode(), sysDic.getCodeText());
                } else {
                    resultMap.get(key).put(sysDic.getCode(), sysDic.getCodeText());
                }
            }
        }
        return resultMap;
    }

    @Override
    @Cacheable(value = Constants.CACHE_NAMESPACE + "sysDics")
    public Map<String, String> queryDicByTypeMap(Map<String, Object> params) {
        return queryDicByType((String) params.get("type"));
    }

    @Override
    @Cacheable(value = Constants.CACHE_NAMESPACE + "sysDics")
    public Map<String, String> queryDicByType(String key) {
        Map<String, Object> params = InstanceUtil.newHashMap();
        params.put("type", key);
        List<SysDic> list = queryList(params);
        Map<String, String> resultMap = InstanceUtil.newHashMap();
        for (SysDic sysDic : list) {
            resultMap.put(sysDic.getCode(), sysDic.getCodeText());
        }
        return resultMap;
    }

    @Override
    @Cacheable(value = Constants.CACHE_NAMESPACE + "sysDics")
    public String getText(String parentType, String parentCode, String type, String code) {
        Map<String, Object> params = InstanceUtil.newHashMap();
        params.put("parentType", parentType);
        params.put("parentCode", parentCode);
        params.put("type", type);
        params.put("code", code);
        List<SysDic> list = queryList(params);
        if (list.isEmpty()) {
            return "";
        }
        return list.get(0).getCodeText();
    }

    @Override
    @Cacheable(value = Constants.CACHE_NAMESPACE + "sysDics")
    public List<SysDic> getDics(String parentType, String parentCode, String type) {
        Map<String, Object> map = InstanceUtil.newHashMap();
        map.put("type", type);
        map.put("parentType", parentType);
        map.put("parentCode", parentCode);
        map.put("orderBy", "type_,sort_no");
        List<SysDic> list = queryList(map);
        return list;
    }
}
