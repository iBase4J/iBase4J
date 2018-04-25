package org.ibase4j.service.sys;

import java.util.List;
import java.util.Map;

import org.ibase4j.model.sys.SysMenu;

import top.ibase4j.core.base.IBaseService;

/**
 * @author ShenHuaJie
 * @since 2018年4月24日 上午10:58:56
 */
public interface ISysMenuService extends IBaseService<SysMenu> {
    /** 获取所有权限选项(value-text) */
    public List<Map<String, String>> getPermissions();

    public List<?> queryTreeList(Map<String, Object> param);
}
