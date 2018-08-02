package org.ibase4j.service.sys.impl;

import java.util.List;
import java.util.Map;

import org.ibase4j.mapper.sys.SysDeptMapper;
import org.ibase4j.model.sys.SysDept;
import org.ibase4j.service.sys.SysDeptService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import top.ibase4j.core.base.BaseServiceImpl;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@Service
@CacheConfig(cacheNames = "sysDept")
public class SysDeptServiceImpl extends BaseServiceImpl<SysDept, SysDeptMapper> implements SysDeptService {
    @Override
    public List<SysDept> queryList(Map<String, Object> params) {
        List<SysDept> page = super.queryList(params);
        for (SysDept sysDept : page) {
            if (sysDept != null) {
                if (sysDept.getParentId() != null) {
                    SysDept parent = super.queryById(sysDept.getParentId());
                    if (parent != null) {
                        sysDept.setParentName(parent.getDeptName());
                    } else {
                        sysDept.setParentId(null);
                    }
                }
            }
        }
        return page;
    }
}
