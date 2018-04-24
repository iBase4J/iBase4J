package org.ibase4j.service.impl;

import org.ibase4j.mapper.SysDeptMapper;
import org.ibase4j.model.SysDept;
import org.ibase4j.service.ISysDeptService;
import org.springframework.cache.annotation.CacheConfig;

import com.alibaba.dubbo.config.annotation.Service;
import com.weibo.api.motan.config.springsupport.annotation.MotanService;

import top.ibase4j.core.base.BaseService;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@CacheConfig(cacheNames = "sysDept")
@Service(interfaceClass = ISysDeptService.class)
@MotanService(interfaceClass = ISysDeptService.class)
public class SysDeptServiceImpl extends BaseService<SysDept, SysDeptMapper> implements ISysDeptService {
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
