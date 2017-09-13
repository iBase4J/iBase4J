package org.ibase4j.service;

import org.ibase4j.core.base.BaseService;
import org.ibase4j.model.SysUnit;
import org.ibase4j.provider.ISysUnitProvider;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * 单位管理
 * @author ShenHuaJie
 */
@Service
public class SysUnitService extends BaseService<ISysUnitProvider, SysUnit> {
	@Reference
	public void setProvider(ISysUnitProvider provider) {
		this.provider = provider;
	}
}
