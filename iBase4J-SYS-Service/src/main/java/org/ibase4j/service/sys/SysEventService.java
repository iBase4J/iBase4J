package org.ibase4j.service.sys;

import java.util.Map;

import org.ibase4j.core.base.BaseService;
import org.ibase4j.model.sys.SysEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;

@Service
@CacheConfig(cacheNames = "sysEvent")
public class SysEventService extends BaseService<SysEvent> {
	@Autowired
	private SysUserService sysUserService;

	public Page<Map<String, Object>> queryMap(Map<String, Object> params) {
		Page<Map<String, Object>> page = super.queryMap(params);
		for (Map<String, Object> map : page.getRecords()) {
			Long createBy = (Long) map.get("createBy");
			if (createBy != null) {
				map.put("userName", sysUserService.queryById(createBy).getUserName());
			}
		}
		return page;
	}
}
