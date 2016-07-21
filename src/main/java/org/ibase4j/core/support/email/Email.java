package org.ibase4j.core.support.email;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Email implements Serializable {
	private String host;
	private String from;
	private String name;
	private String password;
	private String key;
	private String sendTo;
	private String copyTo;
	private String topic;
	private String body;
	private String[] fileAffix;

	public Email() {
	}

	/**
	 * @param sendTo 接收人
	 * @param topic 主题
	 * @param body 内容
	 */
	public Email(String sendTo, String topic, String body) {
		this(null, sendTo, null, topic, body, null);
	}

	/**
	 * @param sendTo 接收人
	 * @param topic 主题
	 * @param body 内容
	 * @param fileAffix 附件
	 */
	public Email(String sendTo, String topic, String body, String[] fileAffix) {
		this(sendTo, null, topic, body, fileAffix);
	}

	/**
	 * @param sendTo 接收人
	 * @param copyTo 抄送人
	 * @param topic 主题
	 * @param body 内容
	 */
	public Email(String sendTo, String copyTo, String topic, String body) {
		this(null, sendTo, copyTo, topic, body, null);
	}

	/**
	 * @param sendTo 接收人
	 * @param copyTo 抄送人
	 * @param topic 主题
	 * @param body 内容
	 * @param fileAffix 附件
	 */
	public Email(String sendTo, String copyTo, String topic, String body, String[] fileAffix) {
		this(null, sendTo, copyTo, topic, body, fileAffix);
	}

	/**
	 * @param from 发送人
	 * @param sendTo 接收人
	 * @param copyTo 抄送人
	 * @param topic 主题
	 * @param body 内容
	 */
	public Email(String from, String sendTo, String copyTo, String topic, String body) {
		this(from, sendTo, copyTo, topic, body, null);
	}

	/**
	 * @param from 发送人
	 * @param sendTo 接收人
	 * @param copyTo 抄送人
	 * @param topic 主题
	 * @param body 内容
	 * @param fileAffix 附件
	 */
	public Email(String from, String sendTo, String copyTo, String topic, String body, String[] fileAffix) {
		this(from, null, null, null, sendTo, copyTo, topic, body, fileAffix);
	}

	/**
	 * @param from 发送人
	 * @param name 登录名
	 * @param password 登录密码
	 * @param sendTo 接收人
	 * @param copyTo 抄送人
	 * @param topic 主题
	 * @param body 内容
	 */
	public Email(String from, String name, String password, String key, String sendTo, String copyTo, String topic,
			String body) {
		this(null, from, name, password, key, sendTo, copyTo, topic, body, null);
	}

	/**
	 * @param from 发送人
	 * @param name 登录名
	 * @param password 登录密码
	 * @param sendTo 接收人
	 * @param copyTo 抄送人
	 * @param topic 主题
	 * @param body 内容
	 * @param fileAffix 附件
	 */
	public Email(String from, String name, String password, String key, String sendTo, String copyTo, String topic,
			String body, String[] fileAffix) {
		this(null, from, name, password, key, sendTo, copyTo, topic, body, fileAffix);
	}

	/**
	 * @param host 服务器地址
	 * @param from 发送人
	 * @param name 登录名
	 * @param password 登录密码
	 * @param sendTo 接收人
	 * @param copyTo 抄送人
	 * @param topic 主题
	 * @param body 内容
	 */
	public Email(String host, String from, String name, String password, String key, String sendTo, String copyTo,
			String topic, String body) {
		this(host, from, name, password, key, sendTo, copyTo, topic, body, null);
	}

	/**
	 * @param host 服务器地址
	 * @param from 发送人
	 * @param name 登录名
	 * @param password 登录密码
	 * @param sendTo 接收人
	 * @param copyTo 抄送人
	 * @param topic 主题
	 * @param body 内容
	 * @param fileAffix 附件
	 */
	public Email(String host, String from, String name, String password, String key, String sendTo, String copyTo,
			String topic, String body, String[] fileAffix) {
		this.host = host;
		this.from = from;
		this.name = name;
		this.password = password;
		this.key = key;
		this.sendTo = sendTo;
		this.copyTo = copyTo;
		this.topic = topic;
		this.body = body;
		this.fileAffix = fileAffix;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSendTo() {
		return sendTo;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}

	public String getCopyTo() {
		return copyTo;
	}

	public void setCopyTo(String copyTo) {
		this.copyTo = copyTo;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String[] getFileAffix() {
		return fileAffix;
	}

	public void setFileAffix(String[] fileAffix) {
		this.fileAffix = fileAffix;
	}
}
