package org.ibase4j.core.support.exception;

import org.ibase4j.core.support.HttpCode;

@SuppressWarnings("serial")
public class LoginException extends BaseException {
	public LoginException() {
	}

	public LoginException(String message) {
		super(message);
	}

	public LoginException(String message, Exception e) {
		super(message, e);
	}

	protected HttpCode getHttpCode() {
		return HttpCode.LOGIN_FAIL;
	}
}
