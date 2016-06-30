package org.ibase4j.service.sys;

import java.util.Map;

import org.ibase4j.core.base.BaseService;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboReference;
import org.ibase4j.model.generator.SysRole;
import org.ibase4j.provider.sys.SysRoleProvider;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

/**
 * @author ShenHuaJie
 * @version 2016年5月31日 上午11:09:29
 */
@Service
public class SysRoleService extends BaseService<SysRoleProvider, SysRole> {
	@DubboReference
	public void setProvider(SysRoleProvider provider) {
		this.provider = provider;
	}

	public PageInfo<?> queryBean(Map<String, Object> params) {
		return provider.queryBean(params);
	}
}