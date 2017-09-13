package org.ibase4j.service;

import java.util.List;
import java.util.Map;

import org.ibase4j.core.base.BaseService;
import org.ibase4j.model.SysMenu;
import org.ibase4j.provider.ISysMenuProvider;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:16:07
 */
@Service
public class SysMenuService extends BaseService<ISysMenuProvider, SysMenu> {
	@Reference
	public void setProvider(ISysMenuProvider provider) {
		this.provider = provider;
	}

	public List<Map<String, String>> getPermissions() {
		return provider.getPermissions();
	}
}
