package org.ibase4j.core.interceptor;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.util.Base64;

import com.alibaba.fastjson.JSON;

import top.ibase4j.core.Constants;
import top.ibase4j.core.interceptor.BaseInterceptor;
import top.ibase4j.core.support.HttpCode;
import top.ibase4j.core.support.security.coder.MDCoder;
import top.ibase4j.core.support.security.coder.RSACoder;
import top.ibase4j.core.util.CacheUtil;
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
        // 请求秘钥的接口不需要签名
        String url = request.getRequestURL().toString();
        if (url.contains("/app/secret.api") || url.contains("swagger") || url.contains("/webjars")
            || url.contains("/v2/api-docs") || request.getHeader("god") != null) {
            logger.info("SignInterceptor skip");
            return super.preHandle(request, response, handler);
        }
        // 判断密钥是否过期
        String uuid = request.getHeader("UUID");
        if (uuid == null) {
            return true;
        }
        String publicKey = (String)CacheUtil.getCache().get(Constants.SYSTEM_CACHE_NAMESPACE + "SIGN:" + uuid);
        if (publicKey == null) {
            response.setContentType("text/html; charset=UTF-8");
            Map<String, Object> modelMap = InstanceUtil.newLinkedHashMap();
            modelMap.put("code", HttpCode.NOT_EXTENDED.value());
            modelMap.put("msg", "密钥已过期");
            modelMap.put("timestamp", System.currentTimeMillis());
            PrintWriter out = response.getWriter();
            out.println(JSON.toJSONString(modelMap));
            out.flush();
            out.close();
            return false;
        }
        // 获取参数
        Map<String, Object> params = WebUtil.getParameterMap(request);
        String[] keys = params.keySet().toArray(new String[]{});
        Arrays.sort(keys);
        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            if (!"sign".equals(key) && !"dataFile".equals(key)) {
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
        String data = Base64.encodeBase64String(MDCoder.encodeMD5(kv.getBytes("UTF-8")));
        String sign = (String)params.get("sign");
        if (sign == null) {
            throw new IllegalArgumentException("请求参数未签名");
        }

        if (!RSACoder.verify(data.getBytes("UTF-8"), Base64.decodeBase64(publicKey), Base64.decodeBase64(sign))) {
            response.setContentType("text/html; charset=UTF-8");
            Map<String, Object> modelMap = InstanceUtil.newLinkedHashMap();
            modelMap.put("code", HttpCode.FORBIDDEN.value());
            modelMap.put("msg", HttpCode.FORBIDDEN.msg());
            modelMap.put("timestamp", System.currentTimeMillis());
            PrintWriter out = response.getWriter();
            out.println(JSON.toJSONString(modelMap));
            out.flush();
            out.close();
            return false;
        }
        logger.info("SignInterceptor successful");
        return super.preHandle(request, response, handler);
    }
}
