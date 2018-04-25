package org.ibase4j.service.sys.impl;

import org.ibase4j.mapper.sys.SysDeptMapper;
import org.ibase4j.model.sys.SysDept;
import org.ibase4j.service.sys.ISysDeptService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import top.ibase4j.core.base.BaseService;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@Service
@CacheConfig(cacheNames = "sysDept")
public class SysDeptServiceImpl extends BaseService<SysDept, SysDeptMapper> implements ISysDeptService {
    @Override
    public SysDept queryById(Long id) {
        SysDept sysDept = super.queryById(id);
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
        return sysDept;
    }
}
