package org.ibase4j.provider.sys;

import java.util.List;
import java.util.Map;

import org.ibase4j.core.base.BaseProvider;
import org.ibase4j.model.sys.SysMenu;
import org.ibase4j.model.sys.ext.SysMenuBean;

import com.baomidou.mybatisplus.plugins.Page;

public interface ISysMenuProvider extends BaseProvider<SysMenu> {

	public Page<SysMenuBean> queryBeanPage(Map<String, Object> params);

	public List<SysMenuBean> queryBean(Map<String, Object> params);

	/** 获取所有权限选项(value-text) */
	public List<Map<String, String>> getPermissions();
}
