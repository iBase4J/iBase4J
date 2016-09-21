package org.ibase4j.model.sys;

import org.ibase4j.core.base.BaseModel;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * SysUser实体类
 * @author ShenHuaJie
 * @version 2016-08-27 22:39:42
 */
@TableName("sys_user")
@SuppressWarnings("serial")
public class SysUser extends BaseModel {
    @TableField("account_")
	private String account;
    @TableField("password_")
	private String password;
    @TableField("phone_")
	private String phone;
    @TableField("sex_")
	private Integer sex;
	private String userName;
    @TableField("avatar_")
	private String avatar;
	private Integer userType;
	private String deptId;
    @TableField("locked_")
	private Integer locked;

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAccount() {
		return this.account;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getAvatar() {
		return this.avatar;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getUserType() {
		return this.userType;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public Integer getLocked() {
		return this.locked;
	}

}
