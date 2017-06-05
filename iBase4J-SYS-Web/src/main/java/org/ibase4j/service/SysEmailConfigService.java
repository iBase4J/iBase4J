package org.ibase4j.service;

import org.springframework.stereotype.Service;
import org.ibase4j.core.base.BaseService;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboReference;
import org.ibase4j.model.SysEmailConfig;
import org.ibase4j.provider.ISysEmailConfigProvider;

/**
 * 邮件配置管理
 * @author ShenHuaJie
 */
@Service
public class SysEmailConfigService extends BaseService<ISysEmailConfigProvider, SysEmailConfig> {
	@DubboReference
	public void setProvider(ISysEmailConfigProvider provider) {
		this.provider = provider;
	}
}
