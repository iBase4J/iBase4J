package org.ibase4j.service.sys;

import java.util.List;
import java.util.Map;

import org.ibase4j.core.base.BaseModel;
import org.ibase4j.core.base.BaseService;
import org.ibase4j.dao.sys.SysMenuMapper;
import org.ibase4j.model.sys.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@Service
@CacheConfig(cacheNames = "sysMenu")
public class SysMenuService extends BaseService<SysMenu> {
	@Autowired
	private SysDicService sysDicService;

	public List<SysMenu> queryList(Map<String, Object> params) {
		List<SysMenu> pageInfo = super.queryList(params);
		Map<String, String> menuTypeMap = sysDicService.queryDicByType("MENUTYPE");
		EntityWrapper<SysMenu> wrapper = new EntityWrapper<SysMenu>();
		for (SysMenu sysMenu : pageInfo) {
			if (sysMenu.getMenuType() != null) {
				sysMenu.setTypeName(menuTypeMap.get(sysMenu.getMenuType().toString()));
			}
			SysMenu menu = new SysMenu();
			menu.setParentId(sysMenu.getId());
			wrapper.setEntity(menu);
			int count = mapper.selectCount(wrapper);
			if (count > 0) {
				sysMenu.setLeaf(0);
			}
		}
		return pageInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ibase4j.provider.SysMenuProvider#getPermissions()
	 */
	public List<Map<String, String>> getPermissions(BaseModel model) {
		return ((SysMenuMapper) mapper).getPermissions();
	}

}
