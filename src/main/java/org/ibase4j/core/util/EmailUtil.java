package org.ibase4j.core.util;

import org.ibase4j.core.support.email.Email;
import org.ibase4j.core.support.email.EmailSender;

/**
 * 发送邮件辅助类
 * 
 * @author ShenHuaJie
 * @version $Id: MailUtil.java, v 0.1 2014年12月4日 下午8:22:43 ShenHuaJie Exp $
 */
public final class EmailUtil {
	private EmailUtil() {
	}

	/**
	 * 发送邮件
	 */
	public static final boolean sendEmail(Email email) {
		// 初始化邮件引擎
		EmailSender sender = new EmailSender(email.getHost());
		sender.setNamePass(email.getName(), email.getPassword(), email.getKey());
		if (sender.setFrom(email.getFrom()) == false)
			return false;
		if (sender.setTo(email.getSendTo()) == false)
			return false;
		if (email.getCopyTo() != null && sender.setCopyTo(email.getCopyTo()) == false)
			return false;
		if (sender.setSubject(email.getTopic()) == false)
			return false;
		if (sender.setBody(email.getBody()) == false)
			return false;
		if (email.getFileAffix() != null) {
			for (int i = 0; i < email.getFileAffix().length; i++) {
				if (sender.addFileAffix(email.getFileAffix()[i]) == false)
					return false;
			}
		}
		// 发送
		return sender.sendout();
	}
}
