package org.ibase4j.provider.sys;

import java.util.Map;

import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.dao.generator.SysEventMapper;
import org.ibase4j.model.generator.SysEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;

@DubboService(interfaceClass = SysEventProvider.class)
public class SysEventProviderImpl extends BaseProviderImpl<SysEvent> implements SysEventProvider {
	@Autowired
	private SysEventMapper sysEventMapper;

	protected Object getMapper() {
		return sysEventMapper;
	}

	public PageInfo<SysEvent> query(Map<String, Object> params) {
		return null;
	}
}
