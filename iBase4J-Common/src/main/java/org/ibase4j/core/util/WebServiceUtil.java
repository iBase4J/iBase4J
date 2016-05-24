package org.ibase4j.core.util;

import java.net.URL;

import org.codehaus.xfire.client.Client;

/**
 * @author ShenHueJie
 * @version 2016年5月24日 下午5:25:11
 */
public final class WebServiceUtil {
	private WebServiceUtil() {
	}

	/** 调用webService */
	public static final Object invoke(String url, String method, Object... params) {
		try {
			Client client = new Client(new URL(url + "?wsdl"));
			return client.invoke(method, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
