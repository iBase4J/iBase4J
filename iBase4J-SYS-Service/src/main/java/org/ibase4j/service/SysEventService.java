package org.ibase4j.service;

import java.util.Map;

import org.ibase4j.core.base.BaseService;
import org.ibase4j.model.SysEvent;
import org.ibase4j.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;

@Service
@CacheConfig(cacheNames = "sysEvent")
public class SysEventService extends BaseService<SysEvent> {
	@Autowired
	private SysUserService sysUserService;

	public Page<SysEvent> query(Map<String, Object> params) {
		Page<SysEvent> page = super.query(params);
		for (SysEvent sysEvent : page.getRecords()) {
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
		return page;
	}
}
