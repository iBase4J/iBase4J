package org.ibase4j.service.sys;

import java.util.Map;

import org.ibase4j.mapper.sys.SysEventMapper;
import org.ibase4j.model.sys.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import top.ibase4j.core.base.BaseService;
import top.ibase4j.core.support.Pagination;
import top.ibase4j.model.SysEvent;

@Service
@CacheConfig(cacheNames = "sysEvent")
public class SysEventService extends BaseService<SysEvent, SysEventMapper> {
	@Autowired
	private SysUserService sysUserService;

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
