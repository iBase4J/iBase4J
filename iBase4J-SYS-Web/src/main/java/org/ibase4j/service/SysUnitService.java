package org.ibase4j.service;

import org.springframework.stereotype.Service;
import org.ibase4j.core.base.BaseService;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboReference;
import org.ibase4j.model.SysUnit;
import org.ibase4j.provider.ISysUnitProvider;

/**
 * 单位管理
 * @author ShenHuaJie
 */
@Service
public class SysUnitService extends BaseService<ISysUnitProvider, SysUnit> {
	@DubboReference
	public void setProvider(ISysUnitProvider provider) {
		this.provider = provider;
	}
}
