package org.ibase4j.model.sys;

import java.util.List;

import org.ibase4j.model.generator.SysMenu;

@SuppressWarnings("serial")
public class SysMenuBean extends SysMenu {
	private List<SysMenuBean> menuBeans;

	public List<SysMenuBean> getMenuBeans() {
		return menuBeans;
	}

	public void setMenuBeans(List<SysMenuBean> menuBeans) {
		this.menuBeans = menuBeans;
	}
}
