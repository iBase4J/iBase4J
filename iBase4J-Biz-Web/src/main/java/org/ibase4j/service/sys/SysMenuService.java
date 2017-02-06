package org.ibase4j.service.sys;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.ibase4j.core.base.BaseService;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboReference;
import org.ibase4j.model.sys.SysMenu;
import org.ibase4j.model.sys.ext.SysMenuBean;
import org.ibase4j.provider.sys.ISysMenuProvider;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:16:07
 */
@Service
public class SysMenuService extends BaseService<ISysMenuProvider, SysMenu> {
	@DubboReference
	public void setProvider(ISysMenuProvider provider) {
		this.provider = provider;
	}

	/** 条件查询 */
	public List<SysMenuBean> queryBean(Map<String, Object> params) {
		return provider.queryBean(params);
	}

	public List<Map<String, String>> getPermissions() {
		return provider.getPermissions();
	}
}
