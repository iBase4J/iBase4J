package org.ibase4j.service.sys;

import org.springframework.stereotype.Service;
import org.ibase4j.core.base.BaseService;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboReference;
import org.ibase4j.model.sys.SysEmailTemplate;
import org.ibase4j.provider.sys.ISysEmailTemplateProvider;

/**
 * 邮件模版管理
 * @author ShenHuaJie
 */
@Service
public class SysEmailTemplateService extends BaseService<ISysEmailTemplateProvider, SysEmailTemplate> {
	@DubboReference
	public void setProvider(ISysEmailTemplateProvider provider) {
		this.provider = provider;
	}
}
