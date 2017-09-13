package org.ibase4j.service;

import org.ibase4j.core.base.BaseService;
import org.ibase4j.model.SysParam;
import org.ibase4j.provider.ISysParamProvider;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * @author ShenHuaJie
 * @version 2016年5月31日 上午11:09:40
 */
@Service
public class SysParamService extends BaseService<ISysParamProvider, SysParam> {
	@Reference
	public void setProvider(ISysParamProvider provider) {
		this.provider = provider;
	}
}
