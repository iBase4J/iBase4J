package org.ibase4j.mapper;

import java.util.List;
import java.util.Map;

import org.ibase4j.model.SysMenu;

import top.ibase4j.core.base.BaseMapper;

/**
 * @author ShenHuaJie
 * @since 2018年3月3日 下午7:23:23
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
	/** 获取所有权限 */
	public List<Map<String, String>> getPermissions();
}