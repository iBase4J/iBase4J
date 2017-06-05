package org.ibase4j.service;

import org.springframework.stereotype.Service;
import org.ibase4j.core.base.BaseService;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboReference;
import org.ibase4j.model.SysNews;
import org.ibase4j.provider.ISysNewsProvider;

/**
 * 新闻管理
 * @author ShenHuaJie
 */
@Service
public class SysNewsService extends BaseService<ISysNewsProvider, SysNews> {
	@DubboReference
	public void setProvider(ISysNewsProvider provider) {
		this.provider = provider;
	}
}
