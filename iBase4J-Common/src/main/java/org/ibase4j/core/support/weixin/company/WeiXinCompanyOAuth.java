package org.ibase4j.core.support.weixin.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author ShenHuaJie
 * @since 2017年2月3日 下午5:15:32
 */
public class WeiXinCompanyOAuth {
	public static String getUserInfo(String code, int agentid) {
		String urlStr = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token="+WeiXinCompanyUtils.getToken()+
//				"&code="+code+"&agentid="+CommonUtils.WX_QY_AGENT_TEST;
				"&code="+code+"&agentid="+agentid;
		try {
			URL url = new URL(urlStr);
			HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();

			conn.setDoInput(true);

			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String s = null;
			StringBuilder sb = new StringBuilder();
			while ((s=reader.readLine()) != null) {
				sb.append(s);
			}
			JSONObject jsonObject = JSON.parseObject(sb.toString());
			if(jsonObject.get("UserId") !=null){
				return jsonObject.get("UserId").toString();
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
