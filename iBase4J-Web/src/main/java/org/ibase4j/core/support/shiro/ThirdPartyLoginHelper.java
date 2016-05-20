package org.ibase4j.core.support.shiro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.core.config.Resources;
import org.ibase4j.core.util.HttpUtil;
import org.ibase4j.mybatis.sys.model.ThirdPartyUser;

import com.alibaba.fastjson.JSONObject;

/**
 * 第三方登录辅助类
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:44:45
 */
public final class ThirdPartyLoginHelper {
	private static final Logger logger = LogManager.getLogger();

	/**
	 * 获取QQ用户信息
	 * 
	 * @param token
	 * @param openid
	 */
	public static final ThirdPartyUser getQQUserinfo(String token, String openid) throws Exception {
		ThirdPartyUser user = new ThirdPartyUser();
		String url = Resources.THIRDPARTY.getString("getUserInfoURL_qq");
		url = url + "?format=json&access_token=" + token + "&oauth_consumer_key="
				+ Resources.THIRDPARTY.getString("app_id_qq") + "&openid=" + openid;
		String res = HttpUtil.httpClientPost(url);
		JSONObject json = JSONObject.parseObject(res);
		if (json.getIntValue("ret") == 0) {
			user.setUserName(json.getString("nickname"));
			String img = json.getString("figureurl_qq_2");
			if (img == null || "".equals(img)) {
				img = json.getString("figureurl_qq_1");
			}
			user.setAvatarUrl(img);
			String sex = json.getString("gender");
			if ("女".equals(sex)) {
				user.setGender("0");
			} else {
				user.setGender("1");
			}
		} else {
			throw new IllegalArgumentException(json.getString("msg"));
		}
		return user;
	}

	/** 获取微信用户信息 */
	public static final ThirdPartyUser getWxUserinfo(String token, String openid) throws Exception {
		ThirdPartyUser user = new ThirdPartyUser();
		String url = Resources.THIRDPARTY.getString("getUserInfoURL_wx");
		url = url + "?access_token=" + token + "&openid=" + openid;
		String res = HttpUtil.httpClientPost(url);
		JSONObject json = JSONObject.parseObject(res);
		if (json.getString("errcode") == null) {
			user.setUserName(json.getString("nickname"));
			String img = json.getString("headimgurl");
			if (img != null && !"".equals(img)) {
				user.setAvatarUrl(img);
			}
			String sex = json.getString("sex");
			if ("0".equals(sex)) {
				user.setGender("0");
			} else {
				user.setGender("1");
			}
		} else {
			throw new IllegalArgumentException(json.getString("errmsg"));
		}
		return user;
	}

	/**
	 * 获取新浪用户信息
	 * 
	 * @param token
	 * @param uid
	 * @return
	 */
	public static final ThirdPartyUser getSinaUserinfo(String token, String uid) throws Exception {
		ThirdPartyUser user = new ThirdPartyUser();
		String url = Resources.THIRDPARTY.getString("getUserInfoURL_sina");
		url = url + "?access_token=" + token + "&uid=" + uid;
		String res = HttpUtil.httpClientPost(url);
		JSONObject json = JSONObject.parseObject(res);
		String name = json.getString("name");
		String nickName = StringUtils.isBlank(json.getString("screen_name")) ? name : json.getString("screen_name");
		user.setAvatarUrl(json.getString("avatar_large"));
		user.setUserName(nickName);
		if ("f".equals(json.getString("gender"))) {
			user.setGender("0");
		} else {
			user.setGender("1");
		}
		user.setToken(token);
		user.setOpenid(uid);
		user.setProvider("sina");
		return user;
	}

	/**
	 * 获取QQ的认证token和用户OpenID
	 * 
	 * @param code
	 * @param type
	 * @return
	 */
	public static final Map<String, String> getQQTokenAndOpenid(String code, String host) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		// 获取令牌
		String tokenUrl = Resources.THIRDPARTY.getString("accessTokenURL_qq");
		tokenUrl = tokenUrl + "?grant_type=authorization_code&client_id=" + Resources.THIRDPARTY.getString("app_id_qq")
				+ "&client_secret=" + Resources.THIRDPARTY.getString("app_key_qq") + "&code=" + code
				+ "&redirect_uri=http://" + host + Resources.THIRDPARTY.getString("redirect_url_qq");
		String tokenRes = HttpUtil.httpClientPost(tokenUrl);
		if (tokenRes != null && tokenRes.indexOf("access_token") > -1) {
			Map<String, String> tokenMap = toMap(tokenRes);
			map.put("access_token", tokenMap.get("access_token"));
			// 获取QQ用户的唯一标识openID
			String openIdUrl = Resources.THIRDPARTY.getString("getOpenIDURL_qq");
			openIdUrl = openIdUrl + "?access_token=" + tokenMap.get("access_token");
			String openIdRes = HttpUtil.httpClientPost(openIdUrl);
			int i = openIdRes.indexOf("(");
			int j = openIdRes.indexOf(")");
			openIdRes = openIdRes.substring(i + 1, j);
			JSONObject openidObj = JSONObject.parseObject(openIdRes);
			map.put("openId", openidObj.getString("openid"));
		} else {
			throw new IllegalArgumentException(Resources.getMessage("THIRDPARTY.LOGIN.NOTOKEN", "QQ"));
		}
		return map;
	}

	/**
	 * 获取微信的认证token和用户OpenID
	 * 
	 * @param code
	 * @param type
	 * @return
	 */
	public static final Map<String, String> getWxTokenAndOpenid(String code, String host) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		// 获取令牌
		String tokenUrl = Resources.THIRDPARTY.getString("accessTokenURL_wx");
		tokenUrl = tokenUrl + "?appid=" + Resources.THIRDPARTY.getString("app_id_wx") + "&secret="
				+ Resources.THIRDPARTY.getString("app_key_wx") + "&code=" + code + "&grant_type=authorization_code";
		String tokenRes = HttpUtil.httpClientPost(tokenUrl);
		if (tokenRes != null && tokenRes.indexOf("access_token") > -1) {
			Map<String, String> tokenMap = toMap(tokenRes);
			map.put("access_token", tokenMap.get("access_token"));
			// 获取微信用户的唯一标识openid
			map.put("openId", tokenMap.get("openid"));
		} else {
			throw new IllegalArgumentException(Resources.getMessage("THIRDPARTY.LOGIN.NOTOKEN", "weixin"));
		}
		return map;
	}

	/**
	 * 获取新浪登录认证token和用户id
	 * 
	 * @param code
	 * @param type
	 * @return
	 */
	public static final JSONObject getSinaTokenAndUid(String code, String host) {
		JSONObject json = null;
		try {
			// 获取令牌
			String tokenUrl = Resources.THIRDPARTY.getString("accessTokenURL_sina");
			ArrayList<NameValuePair> list = new ArrayList<NameValuePair>();
			NameValuePair params1 = new NameValuePair();
			params1.setName("client_id");
			params1.setValue(Resources.THIRDPARTY.getString("app_id_sina"));
			list.add(params1);
			NameValuePair params2 = new NameValuePair();
			params2.setName("client_secret");
			params2.setValue(Resources.THIRDPARTY.getString("app_key_sina"));
			list.add(params2);
			NameValuePair params3 = new NameValuePair();
			params3.setName("grant_type");
			params3.setValue("authorization_code");
			list.add(params3);
			NameValuePair params4 = new NameValuePair();
			params4.setName("redirect_uri");
			params4.setValue("http://" + host + Resources.THIRDPARTY.getString("redirect_url_sina"));
			list.add(params4);
			NameValuePair params5 = new NameValuePair();
			params5.setName("code");
			params5.setValue(code);
			list.add(params5);
			String tokenRes = HttpUtil.httpClientPost(tokenUrl, list);
			// String tokenRes = httpClient(tokenUrl);
			// {"access_token":"2.00AvYzKGWraycB344b3eb242NUbiQB","remind_in":"157679999","expires_in":157679999,"uid":"5659232590"}
			if (tokenRes != null && tokenRes.indexOf("access_token") > -1) {
				json = JSONObject.parseObject(tokenRes);
			} else {
				throw new IllegalArgumentException(Resources.getMessage("THIRDPARTY.LOGIN.NOTOKEN", "sina"));
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return json;
	}

	/**
	 * 将格式为s1&s2&s3...的字符串转化成Map集合
	 * 
	 * @param str
	 * @return
	 */
	private static final Map<String, String> toMap(String str) {
		Map<String, String> map = new HashMap<String, String>();
		String[] strs = str.split("&");
		for (int i = 0; i < strs.length; i++) {
			String[] ss = strs[i].split("=");
			map.put(ss[0], ss[1]);
		}
		return map;
	}
}
