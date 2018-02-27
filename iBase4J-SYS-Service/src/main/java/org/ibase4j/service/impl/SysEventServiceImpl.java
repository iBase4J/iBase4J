package org.ibase4j.service.impl;

import java.util.Map;

import org.ibase4j.model.SysUser;
import org.ibase4j.service.ISysEventService;
import org.ibase4j.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.weibo.api.motan.config.springsupport.annotation.MotanService;

import top.ibase4j.core.base.BaseService;
import top.ibase4j.model.SysEvent;

/**
 * @author ShenHuaJie
 * @since 2018年2月26日 下午7:54:43
 */
@Service(interfaceClass = ISysEventService.class)
@MotanService(interfaceClass = ISysEventService.class)
@CacheConfig(cacheNames = "sysEvent")
public class SysEventServiceImpl extends BaseService<SysEvent> implements ISysEventService{
	@Autowired
	private ISysUserService sysUserService;

	public Page<SysEvent> query(Map<String, Object> params) {
		Page<SysEvent> page = super.query(params);
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
