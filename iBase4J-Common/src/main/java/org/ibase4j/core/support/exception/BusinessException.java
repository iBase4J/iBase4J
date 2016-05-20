package org.ibase4j.core.support.exception;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@SuppressWarnings("serial")
public class BusinessException extends RuntimeException {
	public BusinessException(String message) {
		super(message);
	}
}
