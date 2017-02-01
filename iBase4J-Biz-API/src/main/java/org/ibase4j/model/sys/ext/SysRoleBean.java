package org.ibase4j.model.sys.ext;

import org.ibase4j.model.sys.SysRole;

@SuppressWarnings("serial")
public class SysRoleBean extends SysRole {
	private String deptName;
	private String permission;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
}
