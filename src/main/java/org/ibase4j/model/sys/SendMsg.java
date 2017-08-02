package org.ibase4j.model.sys;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@SuppressWarnings("serial")
public class SendMsg implements Serializable {

	@ApiModelProperty(value = "短信类型:1.用户注册验证码2.登录确认验证码3.修改密码验证码4.身份验证验证码5.信息变更验证码6.活动确认验证码", required = true)
	private String msgType;
	@ApiModelProperty(value = "手机号", required = true)
	private String phone;
	@ApiModelProperty(value = "短信参数", required = false)
	private String params;
	@ApiModelProperty(value = "发送人", required = false)
	private String sender;

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}
}
