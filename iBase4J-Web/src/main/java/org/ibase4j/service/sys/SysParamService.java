package org.ibase4j.service.sys;

import org.ibase4j.core.support.BaseService;
import org.ibase4j.mybatis.generator.model.SysParam;
import org.ibase4j.provider.sys.SysParamProvider;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

@Service
public class SysParamService extends BaseService<SysParamProvider, SysParam> {
	@Reference
	public void setProvider(SysParamProvider provider) {
		super.setProvider(provider);
	}
}
