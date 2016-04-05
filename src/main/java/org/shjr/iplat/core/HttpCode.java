package org.shjr.iplat.core;

import java.util.HashMap;

/**
 * Ajax 请求时的自定义查询状态码，主要参考Http状态码，但并不完全对应
 * 
 * @author
 * @date 2016-03-28
 */
public class HttpCode {
	private HttpCode() {
	}

	/**
	 * 请求成功
	 */
	public static final int HTTP_CODE_200 = 200;
	public static final String HTTP_MSG_200 = "请求成功";
	/**
	 * 请求参数出错
	 */
	public static final int HTTP_CODE_400 = 400;
	public static final String HTTP_MSG_400 = "请求参数出错";
	/**
	 * 没有登录
	 */
	public static final int HTTP_CODE_401 = 401;
	public static final String HTTP_MSG_401 = "NO LOGIN";
	/**
	 * 没有权限
	 */
	public static final int HTTP_CODE_403 = 403;
	public static final String HTTP_MSG_403 = "NO PERMISSION";
	/**
	 * 找不到页面
	 */
	public static final int HTTP_CODE_404 = 404;
	public static final String HTTP_MSG_404 = "找不到页面";
	/**
	 * 发生冲突
	 */
	public static final int HTTP_CODE_409 = 409;
	public static final String HTTP_MSG_409 = "发生冲突";
	/**
	 * 已删除
	 */
	public static final int HTTP_CODE_410 = 410;
	public static final String HTTP_MSG_410 = "已删除";
	/**
	 * 服务器出错
	 */
	public static final int HTTP_CODE_500 = 500;
	public static final String HTTP_MSG_500 = "服务器出错";

	public static final HashMap<Integer, String> MSG = new HashMap<Integer, String>();
	static {
		MSG.put(HTTP_CODE_200, HTTP_MSG_200);
		MSG.put(HTTP_CODE_400, HTTP_MSG_400);
		MSG.put(HTTP_CODE_401, HTTP_MSG_401);
		MSG.put(HTTP_CODE_403, HTTP_MSG_403);
		MSG.put(HTTP_CODE_404, HTTP_MSG_404);
		MSG.put(HTTP_CODE_403, HTTP_MSG_409);
		MSG.put(HTTP_CODE_404, HTTP_MSG_410);
		MSG.put(HTTP_CODE_500, HTTP_MSG_500);
	}

}
