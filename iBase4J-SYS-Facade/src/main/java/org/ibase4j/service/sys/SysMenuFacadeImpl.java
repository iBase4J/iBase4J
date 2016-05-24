package org.ibase4j.service.sys;

import java.util.Map;

import org.ibase4j.core.support.dubbo.BaseFacadeImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.facade.sys.SysMenuFacade;
import org.ibase4j.mybatis.generator.dao.SysMenuMapper;
import org.ibase4j.mybatis.generator.model.SysMenu;
import org.ibase4j.mybatis.sys.dao.SysMenuExpandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.github.pagehelper.PageInfo;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@CacheConfig(cacheNames = "sysMenu")
@DubboService(interfaceClass = SysMenuFacade.class)
public class SysMenuFacadeImpl extends BaseFacadeImpl<SysMenu> implements SysMenuFacade {
	@Autowired
	private SysMenuMapper sysMenuMapper;
	@Autowired
	private SysMenuExpandMapper sysMenuExpandMapper;
	@Autowired
	private SysDicFacadeImpl sysDicService;

	@Cacheable
	public SysMenu queryById(Integer id) {
		return sysMenuMapper.selectByPrimaryKey(id);
	}

	@CachePut
	public SysMenu update(SysMenu record) {
		if (record.getId() == null) {
			sysMenuMapper.insert(record);
		} else {
			sysMenuMapper.updateByPrimaryKey(record);
		}
		return record;
	}

	@CacheEvict
	public void delete(Integer id) {
		sysMenuMapper.deleteByPrimaryKey(id);
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
