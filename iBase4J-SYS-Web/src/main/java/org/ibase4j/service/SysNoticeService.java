package org.ibase4j.service;

import org.springframework.stereotype.Service;
import org.ibase4j.core.base.BaseService;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboReference;
import org.ibase4j.model.SysNotice;
import org.ibase4j.provider.ISysNoticeProvider;

/**
 * 通知管理
 * @author ShenHuaJie
 */
@Service
public class SysNoticeService extends BaseService<ISysNoticeProvider, SysNotice> {
	@DubboReference
	public void setProvider(ISysNoticeProvider provider) {
		this.provider = provider;
	}
}
