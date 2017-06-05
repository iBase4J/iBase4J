package org.ibase4j.service.sys;

import org.ibase4j.core.base.BaseService;
import org.ibase4j.core.support.ISysEventService;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboReference;
import org.ibase4j.model.SysEvent;
import org.ibase4j.provider.ISysEventProvider;
import org.springframework.stereotype.Service;

@Service
public class SysEventService extends BaseService<ISysEventProvider, SysEvent> implements ISysEventService {
	@DubboReference
	public void setProvider(ISysEventProvider provider) {
		this.provider = provider;
	}
}
