package org.ibase4j.service.sys;

import org.ibase4j.core.support.BaseService;
import org.ibase4j.mybatis.generator.model.SysDept;
import org.ibase4j.provider.sys.SysDeptProvider;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:16:07
 */
@Service
public class SysDeptService extends BaseService<SysDeptProvider, SysDept> {
	@Reference
	public void setProvider(SysDeptProvider provider) {
		this.provider = provider;
	}
}
