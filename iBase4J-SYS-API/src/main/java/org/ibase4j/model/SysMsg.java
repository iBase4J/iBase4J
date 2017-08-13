package org.ibase4j.model;

import org.ibase4j.core.base.BaseModel;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 短信
 * </p>
 *
 * @author ShenHuaJie
 * @since 2017-03-12
 */
@SuppressWarnings("serial")
@TableName("sys_msg")
public class SysMsg extends BaseModel {
	/**
	 * 平台编号
	 */
	@TableField("biz_id")
	private String bizId;
	/**
	 * 类型
	 */
	@TableField("type_")
	private String type;
	/**
	 * 接收短信号码
	 */
	@TableField("phone_")
	private String phone;
	/**
	 * 短信内容
	 */
	@TableField("content_")
	private String content;
	/**
	 * 发送状态
	 */
	@TableField("send_state")
	private String sendState;

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSendState() {
		return sendState;
	}

	public void setSendState(String sendState) {
		this.sendState = sendState;
	}

}