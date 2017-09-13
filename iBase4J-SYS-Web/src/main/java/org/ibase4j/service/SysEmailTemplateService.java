package org.ibase4j.service;

import org.ibase4j.core.base.BaseService;
import org.ibase4j.model.SysEmailTemplate;
import org.ibase4j.provider.ISysEmailTemplateProvider;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * 邮件模版管理
 * @author ShenHuaJie
 */
@Service
public class SysEmailTemplateService extends BaseService<ISysEmailTemplateProvider, SysEmailTemplate> {
	@Reference
	public void setProvider(ISysEmailTemplateProvider provider) {
		this.provider = provider;
	}
}
