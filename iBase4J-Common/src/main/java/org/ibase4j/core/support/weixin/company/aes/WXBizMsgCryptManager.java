package org.ibase4j.core.support.weixin.company.aes;

import java.util.HashMap;
import java.util.Map;

import org.ibase4j.core.util.PropertiesUtil;

/**
 * @author ShenHuaJie
 * @since 2017年2月3日 下午5:22:35
 */
public class WXBizMsgCryptManager {

	private static final String encodingAESKey = "PSN9qq6URMKVMwASaeHaYl6NJcVdYpRAvStwLHTBkSN";
	private static final String token = "rXCcbRex3GVgwI5uJMddWbUlivn3DlW";

	private static Map<String, WXBizMsgCrypt> crypts = new HashMap<String, WXBizMsgCrypt>();

	public static WXBizMsgCrypt getChatWXBizMsgCrypt() {
		return getWXBizMsgCrypt(token, encodingAESKey, PropertiesUtil.getString("WX_QY_CORPID"));
	}

	public static WXBizMsgCrypt getWXBizMsgCrypt(String token, String encodingAesKey, String corpId) {
		WXBizMsgCrypt wxcpt = null;
		String key = corpId + "-" + token;
		if (crypts.containsKey(key)) {
			wxcpt = crypts.get(key);
		} else {
			try {
				wxcpt = new WXBizMsgCrypt(token, encodingAesKey, corpId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return wxcpt;
	}
}
