package org.ibase4j.service.sys;

import org.ibase4j.core.support.BaseService;
import org.ibase4j.mybatis.generator.model.SysRole;
import org.ibase4j.provider.sys.SysRoleProvider;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * @author ShenHuaJie
 * @version 2016年5月31日 上午11:09:29
 */
@Service
public class SysRoleService extends BaseService<SysRoleProvider, SysRole> {
	@Reference
	public void setProvider(SysRoleProvider provider) {
		this.provider = provider;
	}
}