package org.ibase4j.provider.sys;

import java.util.Map;

import org.ibase4j.core.base.BaseMapper;
import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.dao.generator.SysMenuMapper;
import org.ibase4j.dao.sys.SysMenuExpandMapper;
import org.ibase4j.model.generator.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;

import com.github.pagehelper.PageInfo;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@CacheConfig(cacheNames = "sysMenu")
@DubboService(interfaceClass = SysMenuProvider.class)
public class SysMenuProviderImpl extends BaseProviderImpl<SysMenu> implements SysMenuProvider {
	@Autowired
	private SysMenuMapper sysMenuMapper;
	@Autowired
	private SysMenuExpandMapper sysMenuExpandMapper;
	@Autowired
	private SysDicProviderImpl sysDicService;

	@SuppressWarnings("unchecked")
	protected BaseMapper<SysMenu> getMapper() {
		return sysMenuMapper;
	}

	public PageInfo<SysMenu> query(Map<String, Object> params) {
		this.startPage(params);
		PageInfo<SysMenu> pageInfo = getPage(sysMenuExpandMapper.query(params));
		Map<String, String> menuTypeMap = sysDicService.queryDicByDicIndexKey("MENUTYPE");
		for (SysMenu sysMenu : pageInfo.getList()) {
			if (sysMenu.getMenuType() != null) {
				sysMenu.setRemark(menuTypeMap.get(sysMenu.getMenuType().toString()));
			}
		}
		return pageInfo;
	}

}
