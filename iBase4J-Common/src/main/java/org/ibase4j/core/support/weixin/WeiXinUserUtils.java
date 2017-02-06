package org.ibase4j.core.support.weixin;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author ShenHuaJie
 * @since 2017年2月3日 下午5:11:07
 */
public class WeiXinUserUtils {
	public static String getUserList(String next_openid) {
		String token = WeiXinUtils.getToken();
		if (token != null) {
			String urlString = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=" + token;
			if (next_openid != null) {
				urlString = urlString + "&next_openid=" + next_openid;
			}
			try {
				URL url = new URL(urlString);
				HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
				httpsURLConnection.setDoInput(true);
				DataInputStream dataInputStream = new DataInputStream(httpsURLConnection.getInputStream());
				StringBuffer stringBuffer = new StringBuffer();
				int inputByte = dataInputStream.read();
				while (inputByte != -1) {
					stringBuffer.append((char) inputByte);
					inputByte = dataInputStream.read();
				}
				String kfString = stringBuffer.toString();
				return kfString;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	public static String getUserInfo(String openId) {
		String token = WeiXinUtils.getToken();
		if (token != null) {
			String urlString = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + token + "&openid="
					+ openId;
			try {
				URL url = new URL(urlString);
				HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
				httpsURLConnection.setDoInput(true);
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(httpsURLConnection.getInputStream()));
				String line = null;
				StringBuilder stringBuilder = new StringBuilder();
				while ((line = bufferedReader.readLine()) != null) {
					stringBuilder.append(line);
				}
				String kfString = stringBuilder.toString();
				return kfString;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
}
