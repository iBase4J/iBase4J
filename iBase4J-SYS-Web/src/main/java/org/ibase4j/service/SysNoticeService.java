package org.ibase4j.service;

import org.ibase4j.core.base.BaseService;
import org.ibase4j.model.SysNotice;
import org.ibase4j.provider.ISysNoticeProvider;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * 通知管理
 * @author ShenHuaJie
 */
@Service
public class SysNoticeService extends BaseService<ISysNoticeProvider, SysNotice> {
	@Reference
	public void setProvider(ISysNoticeProvider provider) {
		this.provider = provider;
	}
}
