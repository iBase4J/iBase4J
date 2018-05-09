package org.ibase4j.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import top.ibase4j.core.base.BaseModel;

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
    @TableField(value = "smtp_host")
    private String smtpHost;
    /** SMTP服务器端口
     */
    @TableField(value = "smtp_port")
    private String smtpPort;
    /**
     * 名称
     */
    @TableField(value = "sender_name")
    private String senderName;
    /**
     * 发邮件邮箱账号
     */
    @TableField(value = "sender_account")
    private String senderAccount;
    /**
     * 发邮件邮箱密码
     */
    @TableField(value = "sender_password")
    private String senderPassword;
    /**
     * 发送邮箱鉴权
     */
    @TableField(value = "sender_password_auth")
    private String senderPasswordAuth;
    /**
     * 发送邮箱方式
     */
    @TableField(value = "is_ssl")
    private Boolean isSSL;

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

    public String getSenderPasswordAuth() {
        return senderPasswordAuth;
    }

    public void setSenderPasswordAuth(String senderPasswordAuth) {
        this.senderPasswordAuth = senderPasswordAuth;
    }

    public Boolean getIsSSL() {
        return isSSL;
    }

    public void setIsSSL(Boolean isSSL) {
        this.isSSL = isSSL;
    }
}
