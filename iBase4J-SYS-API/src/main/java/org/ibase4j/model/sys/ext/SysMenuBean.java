package org.ibase4j.model.sys.ext;

import java.util.List;

import org.ibase4j.model.sys.SysMenu;

@SuppressWarnings("serial")
public class SysMenuBean extends SysMenu {
	private Integer leaf = 1;
	private List<SysMenuBean> menuBeans;

	public Integer getLeaf() {
		return leaf;
	}

	public void setLeaf(Integer leaf) {
		this.leaf = leaf;
	}

	public List<SysMenuBean> getMenuBeans() {
		return menuBeans;
	}

	public void setMenuBeans(List<SysMenuBean> menuBeans) {
		this.menuBeans = menuBeans;
	}
}
