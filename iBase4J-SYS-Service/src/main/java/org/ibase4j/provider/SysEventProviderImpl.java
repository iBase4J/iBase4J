package org.ibase4j.provider;

import java.util.Map;

import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.model.SysEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.plugins.Page;

@CacheConfig(cacheNames = "sysEvent")
@Service(interfaceClass = ISysEventProvider.class)
public class SysEventProviderImpl extends BaseProviderImpl<SysEvent> implements ISysEventProvider {
	@Autowired
	private ISysUserProvider sysUserProvider;

	public Page<Map<String, Object>> queryMap(Map<String, Object> params) {
		Page<Map<String, Object>> page = super.queryMap(params);
		for (Map<String, Object> map : page.getRecords()) {
			Long createBy = (Long) map.get("createBy");
			if (createBy != null) {
				map.put("userName", sysUserProvider.queryById(createBy).getUserName());
			}
		}
		return page;
	}
}
