package org.ibase4j.provider.sys;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.dao.sys.SysMenuMapper;
import org.ibase4j.model.sys.SysMenu;
import org.ibase4j.model.sys.ext.SysMenuBean;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@CacheConfig(cacheNames = "sysMenu")
@DubboService(interfaceClass = ISysMenuProvider.class)
public class SysMenuProviderImpl extends BaseProviderImpl<SysMenu> implements ISysMenuProvider {
	@Autowired
	private ISysDicProvider sysDicProvider;

	public Page<SysMenuBean> queryBean(Map<String, Object> params) {
		Page<Long> idPage = getPage(params);
		idPage.setRecords(mapper.selectIdPage(idPage, params));
		Page<SysMenuBean> pageInfo = getPage(idPage, SysMenuBean.class);
		Map<String, String> menuTypeMap = sysDicProvider.queryDicByDicIndexKey("MENUTYPE");
		for (SysMenuBean sysMenu : pageInfo.getRecords()) {
			if (sysMenu.getMenuType() != null) {
				sysMenu.setTypeName(menuTypeMap.get(sysMenu.getMenuType().toString()));
			}
		}
		return pageInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ibase4j.provider.SysMenuProvider#getPermissions()
	 */
	@Override
	public List<Map<String, String>> getPermissions() {
		return ((SysMenuMapper) mapper).getPermissions();
	}

}
