package org.ibase4j.core.interceptor;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.util.Base64;

import com.alibaba.fastjson.JSON;

import top.ibase4j.core.interceptor.BaseInterceptor;
import top.ibase4j.core.support.HttpCode;
import top.ibase4j.core.support.security.coder.MDCoder;
import top.ibase4j.core.util.InstanceUtil;
import top.ibase4j.core.util.WebUtil;

/**
 * 签名验证
 * 
 * @author ShenHuaJie
 * @since 2017年6月16日 下午5:44:52
 */
public class SignInterceptor extends BaseInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Map<String, Object> params = WebUtil.getParameterMap(request);
		String[] keys = params.keySet().toArray(new String[] {});
		Arrays.sort(keys);
		StringBuilder sb = new StringBuilder();
		for (String key : keys) {
			if (!"sign".equals(key) && !"token".equals(key) && !"dataFile".equals(key)) {
				if (sb.length() == 0) {
					sb = sb.append(key).append("=").append(params.get(key));
				} else {
					sb = sb.append("&").append(key).append("=").append(params.get(key));
				}
			}
		}
		String kv = null;
		if (sb.length() > 100) {
			kv = sb.substring(0, 100);
		} else {
			kv = sb.toString();
		}
		String sign = Base64.encodeBase64String(MDCoder.encodeMD5(kv.getBytes("UTF-8")));
		if (!sign.equals(params.get("sign"))) {
			response.setContentType("text/html; charset=UTF-8");
			Map<String, Object> modelMap = InstanceUtil.newLinkedHashMap();
			modelMap.put("httpCode", HttpCode.FORBIDDEN.value());
			modelMap.put("msg", HttpCode.FORBIDDEN.msg());
			modelMap.put("timestamp", System.currentTimeMillis());
			PrintWriter out = response.getWriter();
			out.println(JSON.toJSONString(modelMap));
			out.flush();
			out.close();
			return false;
		}
		return super.preHandle(request, response, handler);
	}
}
