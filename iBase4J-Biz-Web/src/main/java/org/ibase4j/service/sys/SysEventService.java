package org.ibase4j.service.sys;

import org.ibase4j.core.base.BaseService;
import org.ibase4j.core.support.ISysEventService;
import org.ibase4j.model.SysEvent;
import org.ibase4j.provider.ISysEventProvider;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

@Service
public class SysEventService extends BaseService<ISysEventProvider, SysEvent> implements ISysEventService {
	@Reference
	public void setProvider(ISysEventProvider provider) {
		this.provider = provider;
	}
}
