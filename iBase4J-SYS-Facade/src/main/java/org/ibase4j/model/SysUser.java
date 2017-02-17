package org.ibase4j.model;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.ibase4j.core.base.BaseModel;
import org.ibase4j.core.util.PinyinUtil;

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
	private String namePinyin;
    @TableField("email_")
	private String email;
	private Date birthDay;
    @TableField("position_")
	private String position;
    @TableField("address_")
	private String address;
    @TableField("avatar_")
	private String avatar;
	private Integer userType;
	private Long deptId;
    @TableField("locked_")
	private Integer locked;
    
    @TableField(exist = false)
    private String oldPassword;
    @TableField(exist = false)
	private String deptName;
    @TableField(exist = false)
	private String userTypeText;
    @TableField(exist = false)
	private String permission;

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

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getDeptId() {
		return this.deptId;
	}

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public String getNamePinyin() {
		if (StringUtils.isBlank(namePinyin) && StringUtils.isNotBlank(userName)) {
			return PinyinUtil.getPinYin(userName).toUpperCase();
		}
		return namePinyin;
	}

	public void setNamePinyin(String namePinyin) {
		this.namePinyin = namePinyin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

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

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
}
