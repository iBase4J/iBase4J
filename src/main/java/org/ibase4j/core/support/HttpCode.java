package org.ibase4j.core.support;

/**
 * Ajax 请求时的自定义查询状态码，主要参考Http状态码，但并不完全对应
 * 
 * @author
 * @date 2016-03-28
 */
public enum HttpCode {
	/** 200请求成功 */
	OK(200, "请求成功"),
	/** 400请求参数出错 */
	BAD_REQUEST(400, "请求参数出错"),
	/** 401没有登录 */
	UNAUTHORIZED(401, "没有登录"),
	/** 403没有权限 */
	FORBIDDEN(403, "没有权限"),
	/** 404找不到页面 */
	NOT_FOUND(404, "找不到页面"),
	/** 408请求超时 */
	REQUEST_TIMEOUT(408, "请求超时"),
	/** 409发生冲突 */
	CONFLICT(409, "发生冲突"),
	/** 410已被删除 */
	GONE(410, "已被删除"),
	/** 423已被锁定 */
	LOCKED(423, "已被锁定"),
	/** 500服务器出错 */
	INTERNAL_SERVER_ERROR(500, "服务器出错");

	private final Integer value;
	private final String msg;

	private HttpCode(Integer value, String msg) {
		this.value = value;
		this.msg = msg;
	}

	/**
	 * Return the integer value of this status code.
	 */
	public Integer value() {
		return this.value;
	}

	public String msg() {
		return this.msg;
	}

	public String toString() {
		return this.value.toString();
	}
}
