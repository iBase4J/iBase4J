package org.ibase4j.core.config;

import java.util.ResourceBundle;

import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:config/email.properties")
public class EmailConfig {

	public EmailConfig() {
		ResourceBundle bundle = ResourceBundle.getBundle("config/email");
		host = bundle.getString("email.smtp.host");
		from = bundle.getString("email.send.from");
		name = bundle.getString("email.user.name");
		password = bundle.getString("email.user.password");
		key = bundle.getString("email.authorisationCode");
	}

	private String host;
	private String from;
	private String name;
	private String password;
	private String key;

	public String getHost() {
		return host;
	}

	public String getFrom() {
		return from;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getKey() {
		return key;
	}
}
