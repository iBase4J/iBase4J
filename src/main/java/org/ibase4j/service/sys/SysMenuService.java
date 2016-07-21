package org.ibase4j.service.sys;

import java.util.Map;

import org.ibase4j.dao.generator.SysMenuMapper;
import org.ibase4j.dao.sys.SysMenuExpandMapper;
import org.ibase4j.model.generator.SysMenu;
import org.ibase4j.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@CacheConfig(cacheNames = "sysMenu")
@Service
public class SysMenuService extends BaseService<SysMenu> {
    @Autowired
    private SysMenuMapper sysMenuMapper;
	@Autowired
	private SysMenuExpandMapper sysMenuExpandMapper;
	@Autowired
	private SysDicService sysDicService;

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

    @Override
    @Cacheable
    protected SysMenu queryById(Integer id) {
        return sysMenuMapper.selectByPrimaryKey(id);
    }

}
