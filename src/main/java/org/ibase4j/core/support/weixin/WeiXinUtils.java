package org.ibase4j.core.support.weixin;

import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.ibase4j.core.util.PropertiesUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author ShenHuaJie
 * @since 2017年2月3日 下午5:11:18
 */
public class WeiXinUtils {
	private static String token;
	private static Long tokenExpire;
	private static Long tokenTime;
	private static String grantType = "client_credential";

	private static void initToken() {
		if (tokenTime == null || tokenExpire == null || System.currentTimeMillis() - tokenTime >= tokenExpire) {
			String uriString = "https://api.weixin.qq.com/cgi-bin/token?grant_type=" + grantType + "&appid="
					+ PropertiesUtil.getString("WX_PUBLIC_APPID") + "&secret="
					+ PropertiesUtil.getString("WX_PUBLIC_SECRET");
			try {
				URL url = new URL(uriString);
				HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
				InputStreamReader inputStreamReader = new InputStreamReader(httpsURLConnection.getInputStream());
				int responseInt = inputStreamReader.read();
				StringBuffer stringBuffer = new StringBuffer();
				while (responseInt != -1) {
					stringBuffer.append((char) responseInt);
					responseInt = inputStreamReader.read();
				}
				String tokenString = stringBuffer.toString();
				JSONObject jsonObject = JSON.parseObject(tokenString);
				if (jsonObject.containsKey("access_token")) {
					tokenTime = System.currentTimeMillis();
					token = jsonObject.getString("access_token");
					tokenExpire = jsonObject.getLong("expires_in");
				} else {
					// TODO 验证错误
					System.out.println(jsonObject.get("errcode"));
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static String getToken() {
		initToken();
		return token;
	}
}
