package org.ibase4j.service;

import java.util.List;
import java.util.Map;

import org.ibase4j.model.SysMenu;

import top.ibase4j.core.base.BaseService;

/**
 * @author ShenHuaJie
 * @since 2018年4月24日 上午10:58:56
 */
public interface SysMenuService extends BaseService<SysMenu> {
    /** 获取所有权限选项(value-text) */
    List<Map<String, String>> getPermissions();

    List<?> queryTreeList(Map<String, Object> param);
}
