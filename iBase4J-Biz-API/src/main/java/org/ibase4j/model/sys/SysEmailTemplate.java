package org.ibase4j.model.sys;

import java.io.Serializable;

import org.ibase4j.core.base.BaseModel;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 邮件模版表
 * </p>
 *
 * @author ShenHuaJie
 * @since 2017-01-29
 */
@SuppressWarnings("serial")
@TableName("sys_email_template")
public class SysEmailTemplate extends BaseModel {

	/**
	 * 邮件名称
	 */
	@TableField(value="email_name")
	private String emailName;
	/**
	 * 发送邮件帐号
	 */
	@TableField(value=" email_account")
	private String  emailAccount;
	/**
	 * 排序号
	 */
	@TableField(value="sort_")
	private Integer sort;
	/**
	 * 内容模板
	 */
	@TableField(value="template_")
	private String template;


	public String getEmailName() {
		return emailName;
	}

	public void setEmailName(String emailName) {
		this.emailName = emailName;
	}

	public String getEmailAccount() {
		return  emailAccount;
	}

	public void setEmailAccount(String  emailAccount) {
		this. emailAccount =  emailAccount;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	protected Serializable pkVal() {
		return getId();
	}

}
