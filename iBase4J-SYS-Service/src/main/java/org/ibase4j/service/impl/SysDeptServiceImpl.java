package org.ibase4j.service.impl;

import org.ibase4j.mapper.SysDeptMapper;
import org.ibase4j.model.SysDept;
import org.ibase4j.service.SysDeptService;
import org.springframework.cache.annotation.CacheConfig;

import com.alibaba.dubbo.config.annotation.Service;
import com.weibo.api.motan.config.springsupport.annotation.MotanService;

import top.ibase4j.core.base.BaseServiceImpl;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@CacheConfig(cacheNames = "sysDept")
@Service(interfaceClass = SysDeptService.class)
@MotanService(interfaceClass = SysDeptService.class)
public class SysDeptServiceImpl extends BaseServiceImpl<SysDept, SysDeptMapper> implements SysDeptService {
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
