package org.ibase4j.core.support.exception;

@SuppressWarnings("serial")
public class DataParseException extends RuntimeException {

	public DataParseException() {
	}

	public DataParseException(Throwable ex) {
		super(ex);
	}

	public DataParseException(String message) {
		super(message);
	}

	public DataParseException(String message, Throwable ex) {
		super(message, ex);
	}
}
