package org.ibase4j.provider.sys.impl;

import java.util.List;
import java.util.Map;

import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.dao.sys.SysMenuMapper;
import org.ibase4j.model.sys.SysMenu;
import org.ibase4j.provider.sys.ISysDicProvider;
import org.ibase4j.provider.sys.ISysMenuProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@CacheConfig(cacheNames = "sysMenu")
@DubboService(interfaceClass = ISysMenuProvider.class)
public class SysMenuProviderImpl extends BaseProviderImpl<SysMenu> implements ISysMenuProvider {
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private ISysDicProvider sysDicProvider;

    public Page<SysMenu> query(Map<String, Object> params) {
        Page<String> idPage = this.getPage(params);
        idPage.setRecords(mapper.selectIdByMap(idPage, params));
        Page<SysMenu> pageInfo = getPage(idPage);
        Map<String, String> menuTypeMap = sysDicProvider.queryDicByDicIndexKey("MENUTYPE");
        for (SysMenu sysMenu : pageInfo.getRecords()) {
            if (sysMenu.getMenuType() != null) {
                sysMenu.setRemark(menuTypeMap.get(sysMenu.getMenuType().toString()));
            }
        }
        return pageInfo;
    }

    /* (non-Javadoc)
     * @see org.ibase4j.provider.SysMenuProvider#getPermissions() */
    @Override
    public List<Map<String, String>> getPermissions() {
        return sysMenuMapper.getPermissions();
    }

}
