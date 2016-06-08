package org.ibase4j.service.sys;

import org.ibase4j.core.support.BaseService;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboReference;
import org.ibase4j.mybatis.generator.model.SysMenu;
import org.ibase4j.provider.sys.SysMenuProvider;
import org.springframework.stereotype.Service;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:16:07
 */
@Service
public class SysMenuService extends BaseService<SysMenuProvider, SysMenu> {
	@DubboReference
	public void setProvider(SysMenuProvider provider) {
		this.provider = provider;
	}
}
