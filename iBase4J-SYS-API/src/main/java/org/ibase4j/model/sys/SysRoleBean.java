package org.ibase4j.model.sys;

import org.ibase4j.model.generator.SysRole;

@SuppressWarnings("serial")
public class SysRoleBean extends SysRole {
	private String permission;

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
}
