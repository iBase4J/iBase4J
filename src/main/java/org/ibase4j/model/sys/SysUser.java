package org.ibase4j.model.sys;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import top.ibase4j.core.base.BaseModel;

/**
 * <p>
 * 用户管理
 * </p>
 *
 * @author ShenHuaJie
 * @since 2017-02-15
 */
@TableName("sys_user")
@SuppressWarnings("serial")
public class SysUser extends BaseModel {

	/**
	 * 登陆帐户
	 */
	@TableField("account_")
	private String account;
	/**
	 * 密码
	 */
	@TableField("password_")
	private String password;
	/**
	 * 用户类型(1普通用户2管理员3系统用户)
	 */
	@TableField("user_type")
	private String userType;
	/**
	 * 姓名
	 */
	@TableField("user_name")
	private String userName;
	/**
	 * 姓名拼音
	 */
	@TableField("name_pinyin")
	private String namePinyin;
	/**
	 * 性别(0:未知;1:男;2:女)
	 */
	@TableField("sex_")
	private Integer sex;
	/**
	 * 头像
	 */
	@TableField("avatar_")
	private String avatar;
	/**
	 * 电话
	 */
	@TableField("phone_")
	private String phone;
	/**
	 * 邮箱
	 */
	@TableField("email_")
	private String email;
	/**
	 * 身份证号码
	 */
	@TableField("id_card")
	private String idCard;
	/**
	 * 微信
	 */
	@TableField("wei_xin")
	private String weiXin;
	/**
	 * 微博
	 */
	@TableField("wei_bo")
	private String weiBo;
	/**
	 * QQ
	 */
	@TableField("qq_")
	private String qq;
	/**
	 * 出生日期
	 */
	@TableField("birth_day")
	private Date birthDay;
	/**
	 * 部门编号
	 */
	@TableField("dept_id")
	private Long deptId;
	/**
	 * 职位
	 */
	@TableField("position_")
	private String position;
	/**
	 * 详细地址
	 */
	@TableField("address_")
	private String address;
	/**
	 * 工号
	 */
	@TableField("staff_no")
	private String staffNo;

	@TableField(exist = false)
	private String oldPassword;
	@TableField(exist = false)
	private String deptName;
	@TableField(exist = false)
	private String userTypeText;
	@TableField(exist = false)
	private String permission;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNamePinyin() {
		return namePinyin;
	}

	public void setNamePinyin(String namePinyin) {
		this.namePinyin = namePinyin;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getWeiXin() {
		return weiXin;
	}

	public void setWeiXin(String weiXin) {
		this.weiXin = weiXin;
	}

	public String getWeiBo() {
		return weiBo;
	}

	public void setWeiBo(String weiBo) {
		this.weiBo = weiBo;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
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