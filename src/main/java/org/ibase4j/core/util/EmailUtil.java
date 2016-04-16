package org.ibase4j.core.util;

import java.io.IOException;
import java.util.Properties;

import org.ibase4j.core.support.EmailEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 发送邮件辅助类
 * 
 * @author ShenHuaJie
 * @version $Id: MailUtil.java, v 0.1 2014年12月4日 下午8:22:43 ShenHuaJie Exp $
 */
public class EmailUtil {
	protected static Logger logger = LoggerFactory.getLogger(EmailUtil.class);

	/**
	 * 发送邮件
	 * 
	 * @param sendTo 接收人
	 * @param topic 主题
	 * @param body 内容
	 * @return
	 */
	public static boolean sendMail(String sendTo, String topic, String body) {
		return sendMail(null, sendTo, null, topic, body, null);
	}

	/**
	 * 发送邮件
	 * 
	 * @param sendTo 接收人
	 * @param topic 主题
	 * @param body 内容
	 * @param fileAffix 附件
	 * @return
	 */
	public static boolean sendMail(String sendTo, String topic, String body, String[] fileAffix) {
		return sendMail(sendTo, null, topic, body, fileAffix);
	}

	/**
	 * 发送邮件
	 * 
	 * @param sendTo 接收人
	 * @param copyTo 抄送人
	 * @param topic 主题
	 * @param body 内容
	 * @return
	 */
	public static boolean sendMail(String sendTo, String copyTo, String topic, String body) {
		return sendMail(null, sendTo, copyTo, topic, body, null);
	}

	/**
	 * 发送邮件
	 * 
	 * @param sendTo 接收人
	 * @param copyTo 抄送人
	 * @param topic 主题
	 * @param body 内容
	 * @param fileAffix 附件
	 * @return
	 */
	public static boolean sendMail(String sendTo, String copyTo, String topic, String body, String[] fileAffix) {
		return sendMail(null, sendTo, copyTo, topic, body, fileAffix);
	}

	/**
	 * 发送邮件
	 * 
	 * @param from 发送人
	 * @param sendTo 接收人
	 * @param copyTo 抄送人
	 * @param topic 主题
	 * @param body 内容
	 * @return
	 */
	public static boolean sendMail(String from, String sendTo, String copyTo, String topic, String body) {
		return sendMail(from, sendTo, copyTo, topic, body, null);
	}

	/**
	 * 发送邮件
	 * 
	 * @param from 发送人
	 * @param sendTo 接收人
	 * @param copyTo 抄送人
	 * @param topic 主题
	 * @param body 内容
	 * @param fileAffix 附件
	 * @return
	 */
	public static boolean sendMail(String from, String sendTo, String copyTo, String topic, String body,
			String[] fileAffix) {
		return sendMail(from, null, null, sendTo, copyTo, topic, body, fileAffix);
	}

	/**
	 * 发送邮件
	 * 
	 * @param from 发送人
	 * @param name 登录名
	 * @param password 登录密码
	 * @param sendTo 接收人
	 * @param copyTo 抄送人
	 * @param topic 主题
	 * @param body 内容
	 * @return
	 */
	public static boolean sendMail(String from, String name, String password, String sendTo, String copyTo,
			String topic, String body) {
		return sendMail(null, from, name, password, sendTo, copyTo, topic, body, null);
	}

	/**
	 * 发送邮件
	 * 
	 * @param from 发送人
	 * @param name 登录名
	 * @param password 登录密码
	 * @param sendTo 接收人
	 * @param copyTo 抄送人
	 * @param topic 主题
	 * @param body 内容
	 * @param fileAffix 附件
	 * @return
	 */
	public static boolean sendMail(String from, String name, String password, String sendTo, String copyTo,
			String topic, String body, String[] fileAffix) {
		return sendMail(null, from, name, password, sendTo, copyTo, topic, body, fileAffix);
	}

	/**
	 * 发送邮件
	 * 
	 * @param host 服务器地址
	 * @param from 发送人
	 * @param name 登录名
	 * @param password 登录密码
	 * @param sendTo 接收人
	 * @param copyTo 抄送人
	 * @param topic 主题
	 * @param body 内容
	 */
	public static boolean sendMail(String host, String from, String name, String password, String sendTo, String copyTo,
			String topic, String body) {
		return sendMail(host, from, name, password, sendTo, copyTo, topic, body, null);
	}

	/**
	 * 发送邮件
	 * 
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
	public static boolean sendMail(String host, String from, String name, String password, String sendTo, String copyTo,
			String topic, String body, String[] fileAffix) {
		try {
			Properties properties = PropertiesLoaderUtils.loadAllProperties("classpath:config/email.properties");
			// 获取邮件服务器配置
			if (host == null || "".equals(host.trim())) {
				host = properties.getProperty("mail.server.host");
			}
			if (from == null || "".equals(from.trim())) {
				from = properties.getProperty("email.send.from");
			}
			if (name == null || "".equals(name.trim())) {
				name = properties.getProperty("mail.name");
			}
			if (password == null || "".equals(password.trim())) {
				password = properties.getProperty("mail.password");
			}
		} catch (IOException e) {
			logger.error("", e);
		}
		// 初始化邮件引擎
		EmailEngine theMail = new EmailEngine(host);
		theMail.setNeedAuth(true);
		if (theMail.setSubject(topic) == false)
			return false;
		if (theMail.setBody(body) == false)
			return false;
		if (theMail.setTo(sendTo) == false)
			return false;
		if (copyTo != null && theMail.setCopyTo(copyTo) == false)
			return false;
		if (theMail.setFrom(from) == false)
			return false;
		if (fileAffix != null) {
			for (int i = 0; i < fileAffix.length; i++) {
				if (theMail.addFileAffix(fileAffix[i]) == false)
					return false;
			}
		}
		theMail.setNamePass(name, password);
		// 发送
		return theMail.sendout();
	}
}
