package org.ibase4j.service.sys;

import org.ibase4j.core.support.BaseService;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboReference;
import org.ibase4j.model.generator.SysParam;
import org.ibase4j.provider.sys.SysParamProvider;
import org.springframework.stereotype.Service;

/**
 * @author ShenHuaJie
 * @version 2016年5月31日 上午11:09:40
 */
@Service
public class SysParamService extends BaseService<SysParamProvider, SysParam> {
	@DubboReference
	public void setProvider(SysParamProvider provider) {
		this.provider = provider;
	}
}
