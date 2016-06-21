package org.ibase4j.core.exception;

/**
 * FTP异常
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@SuppressWarnings("serial")
public class FtpException extends RuntimeException {
	public FtpException() {
	}

	public FtpException(String message) {
		super(message);
	}

	public FtpException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
