package org.ibase4j.service;

import java.util.List;
import java.util.Map;

import org.ibase4j.model.SysMenu;

import top.ibase4j.core.base.IBaseService;

public interface ISysMenuService extends IBaseService<SysMenu> {
	/** 获取所有权限选项(value-text) */
	public List<Map<String, String>> getPermissions();
}
