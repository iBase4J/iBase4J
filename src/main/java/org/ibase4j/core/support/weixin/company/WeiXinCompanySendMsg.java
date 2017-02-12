package org.ibase4j.core.support.weixin.company;

import com.alibaba.fastjson.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信企业号发送消息
 * 
 * @author ShenHuaJie
 * @since 2017年2月3日 下午5:15:52
 */
public class WeiXinCompanySendMsg {
	/**
	 *
	 * @param user
	 *            成员ID
	 * @param agentid
	 *            企业应用的id
	 * @param content
	 *            消息内容
	 * @return
	 * @throws IOException
	 */
	public static String sendTextMsg(String user, int agentid, String content) throws IOException {
		return sendTextMsg(user, null, null, agentid, content, false);
	}

	/**
	 * 发送文字消息，
	 * 
	 * @param touser
	 *            成员ID列表（消息接收者，多个接收者用‘|’分隔，最多支持1000个）。特殊情况：指定为@all，则向关注该企业应用的全部成员发送
	 * @param toparty
	 *            部门ID列表，多个接收者用‘|’分隔，最多支持100个。当touser为@all时忽略本参数s
	 * @param totag
	 *            标签ID列表，多个接收者用‘|’分隔。当touser为@all时忽略本参数
	 * @param agentid
	 *            企业应用的id，整型。可在应用的设置页面查看
	 * @param content
	 *            消息内容
	 * @param safe
	 *            表示是否是保密消息，0表示否，1表示是，默认0
	 * @throws IOException
	 */
	public static String sendTextMsg(String touser, String toparty, String totag, int agentid, String content,
			boolean safe) throws IOException {
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("touser", touser);// 成员ID列表（消息接收者，多个接收者用‘|’分隔，最多支持1000个）。特殊情况：指定为@all，则向关注该企业应用的全部成员发送
		jsonObject.put("toparty", toparty);// 部门ID列表，多个接收者用‘|’分隔，最多支持100个。当touser为@all时忽略本参数

		jsonObject.put("totag", totag);// 标签ID列表，多个接收者用‘|’分隔。当touser为@all时忽略本参数

		jsonObject.put("agentid", agentid + "");// 企业应用的id，整型。可在应用的设置页面查看
		jsonObject.put("msgtype", "text");// 消息类型，此时固定为：text

		JSONObject text = new JSONObject();
		text.put("content", content);

		jsonObject.put("text", text);// 消息内容
		if (safe) {
			jsonObject.put("safe", "1");// 表示是否是保密消息，0表示否，1表示是，默认0
		} else {
			jsonObject.put("safe", "0");// 表示是否是保密消息，0表示否，1表示是，默认0
		}

		String urlStr = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token="
				+ WeiXinCompanyUtils.getToken();
		String parameters = jsonObject.toJSONString();

		reSend(urlStr, parameters, new HashMap<String, Integer>());

		return "";
	}

	/**
	 * 重发
	 * 
	 * @param urlStr
	 * @param parameters
	 * @param count
	 */
	private static void reSend(String urlStr, String parameters, Map<String, Integer> count) throws IOException {
		if (count.get("times") == null) {
			count.put("times", 0);
		}
		int times = count.get("times");
		if (times < 5) {
			URL url = new URL(urlStr);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setUseCaches(false);
			conn.setReadTimeout(3000);
			conn.setConnectTimeout(3000);

			OutputStream output = conn.getOutputStream();
			output.write(parameters.getBytes("utf-8"));
			output.flush();

			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String s = null;
			StringBuilder sb = new StringBuilder();
			while ((s = reader.readLine()) != null) {
				sb.append(s);
			}
			reader.close();
			JSONObject jsonObject = JSONObject.parseObject(sb.toString());
			String errcode = jsonObject.get("errcode").toString();
			if (!errcode.equals("0")) {
				count.put("times", count.get("times") + 1);
				reSend(urlStr, parameters, count);
			}
		}
	}

	/**
	 * 发送多媒体文件
	 * 
	 * @return
	 */
	public static String sendFileMsg(String touser, String toparty, String totag, int agentid, String media_id,
			boolean safe) throws IOException {
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("touser", touser);// 成员ID列表（消息接收者，多个接收者用‘|’分隔，最多支持1000个）。特殊情况：指定为@all，则向关注该企业应用的全部成员发送
		jsonObject.put("toparty", toparty);// 部门ID列表，多个接收者用‘|’分隔，最多支持100个。当touser为@all时忽略本参数

		jsonObject.put("totag", totag);// 标签ID列表，多个接收者用‘|’分隔。当touser为@all时忽略本参数

		jsonObject.put("agentid", agentid + "");// 企业应用的id，整型。可在应用的设置页面查看
		jsonObject.put("msgtype", "file");// 消息类型，此时固定为：text

		JSONObject text = new JSONObject();
		text.put("media_id", media_id);

		jsonObject.put("file", text);// 消息内容
		if (safe) {
			jsonObject.put("safe", "1");// 表示是否是保密消息，0表示否，1表示是，默认0
		} else {
			jsonObject.put("safe", "0");// 表示是否是保密消息，0表示否，1表示是，默认0
		}

		System.out.println(jsonObject);
		String urlStr = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token="
				+ WeiXinCompanyUtils.getToken();
		String parameters = jsonObject.toString();

		URL url = new URL(urlStr);

		System.out.println("url:" + urlStr);
		System.out.println("parameters:" + parameters);

		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);

		OutputStream output = conn.getOutputStream();
		output.write(parameters.getBytes("utf-8"));
		output.flush();

		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
		String s = null;
		StringBuilder sb = new StringBuilder();
		while ((s = reader.readLine()) != null) {
			sb.append(s);
		}
		reader.close();
		System.out.println(sb.toString());
		return sb.toString();
	}

}
