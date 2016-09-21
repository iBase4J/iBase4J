package org.ibase4j.model.sys.ext;

import org.ibase4j.model.sys.SysUser;

@SuppressWarnings("serial")
public class SysUserBean extends SysUser {
	private String deptName;
	private String userTypeText;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getUserTypeText() {
		return userTypeText;
	}

	public void setUserTypeText(String userTypeText) {
		this.userTypeText = userTypeText;
	}
}
