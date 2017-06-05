package org.ibase4j.service;

import org.springframework.stereotype.Service;
import org.ibase4j.core.base.BaseService;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboReference;
import org.ibase4j.model.SysEmail;
import org.ibase4j.provider.ISysEmailProvider;

/**
 * 邮件管理
 * @author ShenHuaJie
 */
@Service
public class SysEmailService extends BaseService<ISysEmailProvider, SysEmail> {
	@DubboReference
	public void setProvider(ISysEmailProvider provider) {
		this.provider = provider;
	}
}
