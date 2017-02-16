package org.ibase4j.model;

import java.io.Serializable;

import org.ibase4j.core.base.BaseModel;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 邮件配置表
 * </p>
 *
 * @author ShenHuaJie
 * @since 2017-01-29
 */
@SuppressWarnings("serial")
@TableName("sys_email_config")
public class SysEmailConfig extends BaseModel {

	/**
	 * SMTP服务器
	 */
	@TableField(value="smtp_host")
	private String smtpHost;
	/**
	 * SMTP服务器端口
	 */
	@TableField(value="smtp_port")
	private String smtpPort;
	/**
	 * 发送方式
	 */
	@TableField(value="send_method")
	private String sendMethod;
	/**
	 * 名称
	 */
	@TableField(value="sender_name")
	private String senderName;
	/**
	 * 发邮件邮箱账号
	 */
	@TableField(value="sender_account")
	private String senderAccount;
	/**
	 * 发邮件邮箱密码
	 */
	@TableField(value="sender_password")
	private String senderPassword;


	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public String getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}

	public String getSendMethod() {
		return sendMethod;
	}

	public void setSendMethod(String sendMethod) {
		this.sendMethod = sendMethod;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderAccount() {
		return senderAccount;
	}

	public void setSenderAccount(String senderAccount) {
		this.senderAccount = senderAccount;
	}

	public String getSenderPassword() {
		return senderPassword;
	}

	public void setSenderPassword(String senderPassword) {
		this.senderPassword = senderPassword;
	}

	protected Serializable pkVal() {
		return getId();
	}

}
