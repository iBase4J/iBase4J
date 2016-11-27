package org.ibase4j.provider.sys;

import java.util.Map;

import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.model.sys.SysEvent;
import org.ibase4j.provider.sys.ISysEventProvider;
import org.springframework.cache.annotation.CacheConfig;

import com.baomidou.mybatisplus.plugins.Page;

@CacheConfig(cacheNames = "sysEvent")
@DubboService(interfaceClass = ISysEventProvider.class)
public class SysEventProviderImpl extends BaseProviderImpl<SysEvent> implements ISysEventProvider {
	
	public Page<SysEvent> query(Map<String, Object> params) {
        Page<Long> page = this.getPage(params);
        page.setRecords(mapper.selectIdByMap(page, params));
        return getPage(page);
	}
}
