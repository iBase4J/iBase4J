package org.ibase4j.service;

import org.ibase4j.core.base.BaseService;
import org.ibase4j.model.SysNews;
import org.ibase4j.provider.ISysNewsProvider;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * 新闻管理
 * @author ShenHuaJie
 */
@Service
public class SysNewsService extends BaseService<ISysNewsProvider, SysNews> {
	@Reference
	public void setProvider(ISysNewsProvider provider) {
		this.provider = provider;
	}
}
