package org.ibase4j.service.impl;

import java.util.Map;

import org.ibase4j.mapper.SysEventMapper;
import org.ibase4j.model.SysUser;
import org.ibase4j.service.SysEventService;
import org.ibase4j.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;

import com.alibaba.dubbo.config.annotation.Service;
import com.weibo.api.motan.config.springsupport.annotation.MotanService;

import top.ibase4j.core.base.BaseServiceImpl;
import top.ibase4j.core.support.Pagination;
import top.ibase4j.model.SysEvent;

@CacheConfig(cacheNames = "sysEvent")
@Service(interfaceClass = SysEventService.class)
@MotanService(interfaceClass = SysEventService.class)
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
