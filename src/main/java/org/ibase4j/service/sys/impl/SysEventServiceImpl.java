package org.ibase4j.service.sys.impl;

import java.util.Map;

import org.ibase4j.mapper.sys.SysEventMapper;
import org.ibase4j.model.sys.SysUser;
import org.ibase4j.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import top.ibase4j.core.base.BaseServiceImpl;
import top.ibase4j.core.support.Pagination;
import top.ibase4j.model.SysEvent;
import top.ibase4j.service.SysEventService;

@Service
@CacheConfig(cacheNames = "sysEvent")
public class SysEventServiceImpl extends BaseServiceImpl<SysEvent, SysEventMapper> implements SysEventService {
    @Autowired
    private SysUserService sysUserService;

    @Override
    public Pagination<SysEvent> query(Map<String, Object> params) {
        Pagination<SysEvent> page = super.query(params);
        for (SysEvent sysEvent : page.getRecords()) {
            if (sysEvent != null) {
                Long createBy = sysEvent.getCreateBy();
                if (createBy != null) {
                    SysUser sysUser = sysUserService.queryById(createBy);
                    if (sysUser != null) {
                        sysEvent.setUserName(sysUser.getUserName());
                    } else {
                        sysEvent.setUserName(createBy.toString());
                    }
                }
            }
        }
        return page;
    }
}
