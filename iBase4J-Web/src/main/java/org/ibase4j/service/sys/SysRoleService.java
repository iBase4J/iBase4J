package org.ibase4j.service.sys;

import org.ibase4j.core.support.BaseService;
import org.ibase4j.mybatis.generator.model.SysRole;
import org.ibase4j.provider.sys.SysRoleProvider;

import com.alibaba.dubbo.config.annotation.Reference;

public class SysRoleService extends BaseService<SysRoleProvider, SysRole> {
	@Reference
	public void setProvider(SysRoleProvider provider) {
		super.setProvider(provider);
	}
}