package org.ibase4j.service;

import org.ibase4j.core.base.BaseService;
import org.ibase4j.model.SysDept;
import org.ibase4j.provider.ISysDeptProvider;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:16:07
 */
@Service
public class SysDeptService extends BaseService<ISysDeptProvider, SysDept> {
	@Reference
	public void setProvider(ISysDeptProvider provider) {
		this.provider = provider;
	}
}
