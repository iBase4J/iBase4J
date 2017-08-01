package org.ibase4j.core.util;

import org.ibase4j.core.Constants;
import org.ibase4j.core.support.Token;

import com.alibaba.fastjson.JSON;

public class TokenUtil {
	public static void setTokenInfo(String token, String value) {
		try {
			Token tokenInfo = new Token();
			tokenInfo.setTime(System.currentTimeMillis());
			tokenInfo.setValue(value);
			CacheUtil.getCache().hset(Constants.TOKEN_KEY, token, JSON.toJSONString(tokenInfo));
		} catch (Exception e) {
			throw new RuntimeException("保存token失败，错误信息：", e);
		}
	}

	public static void delToken(String token) {
		try {
			CacheUtil.getCache().hdel(Constants.TOKEN_KEY, token);
		} catch (Exception e) {
			throw new RuntimeException("删除token失败，错误信息：", e);
		}
	}

	public static Token getTokenInfo(String token) {
		String value = (String) CacheUtil.getCache().hget(Constants.TOKEN_KEY, token);
		Token tokenInfo = JSON.parseObject(value, Token.class);
		return tokenInfo;
	}
}
