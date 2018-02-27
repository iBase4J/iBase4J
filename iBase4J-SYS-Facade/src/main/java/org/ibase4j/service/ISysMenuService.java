package org.ibase4j.service;

import java.util.List;
import java.util.Map;

import org.ibase4j.model.SysMenu;

import top.ibase4j.core.base.BaseModel;
import top.ibase4j.core.base.IBaseService;

public interface ISysMenuService extends IBaseService<SysMenu> {
	public List<Object> queryTreeList(Map<String, Object> params);

	public List<Map<String, String>> getPermissions(BaseModel model);
}
