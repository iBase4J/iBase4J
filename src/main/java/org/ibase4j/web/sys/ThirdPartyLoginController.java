package org.ibase4j.web.sys;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.ibase4j.core.config.Resources;
import org.ibase4j.core.support.login.ThirdPartyLoginHelper;
import org.ibase4j.core.support.login.ThirdPartyUser;
import org.ibase4j.service.sys.SysUserService;
import org.ibase4j.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;

/**
 * 第三方登录控制类
 * 
 * @author ShenHuaJie
 */
@Controller
public class ThirdPartyLoginController extends BaseController {
	@Autowired
	private SysUserService sysUserService;

	@RequestMapping("/sns")
	public void thirdLogin(HttpServletRequest request, HttpServletResponse response, @RequestParam("t") String type)
			throws Exception {
		String url = getRedirectUrl(type, request);
		response.sendRedirect(url);
	}

	@RequestMapping("/sns_success")
	public String thirdLoginsuccess(HttpServletRequest request, HttpServletResponse response) {
		return "/sns/success";
	}

	@RequestMapping("/sns_bind")
	public String thirdLoginbind(HttpServletRequest request, HttpServletResponse response) {
		return "/sns/bind";
	}

	@RequestMapping("/sns_fail")
	public String thirdLoginfail(HttpServletRequest request, HttpServletResponse response) {
		return "/sns/fail";
	}

	@RequestMapping("/callback/wx")
	public String wxCallback(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String host = request.getHeader("host");
		try {
			String code = request.getParameter("code");
			if (StringUtils.isNotBlank(code)) {// 如果不为空
				// 获取token和openid
				Map<String, String> map = ThirdPartyLoginHelper.getWxTokenAndOpenid(code, host);
				String openId = map.get("openId");
				if (StringUtils.isNotBlank(openId)) {// 如果openID存在
					// 获取第三方用户信息存放到session中
					ThirdPartyUser thirdUser = ThirdPartyLoginHelper.getWxUserinfo(map.get("access_token"), openId);
					// 查询是否已经绑定过
					String provider = "WX";
					String userId = sysUserService.queryUserIdByThirdParty(openId, provider);
					if (StringUtils.isBlank(userId)) { // 如果未绑定，创建临时账号并绑定user数据
						thirdUser.setProvider(provider);
						sysUserService.insertThirdPartyUser(thirdUser);
					}
					// 跳转到登录成功界面
					modelMap.put("retUrl", Resources.THIRDPARTY.getString("third_login_success"));
				} else {// 如果未获取到OpenID
					modelMap.put("retUrl", "-1");
				}
			} else {// 如果没有返回令牌，则直接返回到登录页面
				modelMap.put("retUrl", "-1");
			}
		} catch (Exception e) {
			modelMap.put("retUrl", "-1");
			e.printStackTrace();
		}

		return "/sns/redirect";
	}

	@RequestMapping("/callback/qq")
	public String qqCallback(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String host = request.getHeader("host");
		try {
			String code = request.getParameter("code");
			if (StringUtils.isNotBlank(code)) {// 如果不为空
				// 获取token和openid
				Map<String, String> map = ThirdPartyLoginHelper.getQQTokenAndOpenid(code, host);
				String openId = map.get("openId");
				if (StringUtils.isNotBlank(openId)) {// 如果openID存在
					// 获取第三方用户信息存放到session中
					ThirdPartyUser thirdUser = ThirdPartyLoginHelper.getQQUserinfo(map.get("access_token"), openId);
					// 查询是否已经绑定过
					String provider = "QQ";
					String userId = sysUserService.queryUserIdByThirdParty(openId, provider);
					if (StringUtils.isBlank(userId)) { // 如果未绑定，创建临时账号并绑定user数据
						thirdUser.setProvider(provider);
						sysUserService.insertThirdPartyUser(thirdUser);
					}
					// 跳转到登录成功界面
					modelMap.put("retUrl", Resources.THIRDPARTY.getString("third_login_success"));
				} else {// 如果未获取到OpenID
					modelMap.put("retUrl", "-1");
				}
			} else {// 如果没有返回令牌，则直接返回到登录页面
				modelMap.put("retUrl", "-1");
			}
		} catch (Exception e) {
			modelMap.put("retUrl", "-1");
			e.printStackTrace();
		}

		return "/sns/redirect";
	}

	@RequestMapping("callback/sina")
	public String sinaCallback(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String host = request.getHeader("host");
		try {
			String code = request.getParameter("code");
			if (StringUtils.isNotBlank(code)) {// 如果不为空
				// 获取token和uid
				JSONObject json = ThirdPartyLoginHelper.getSinaTokenAndUid(code, host);
				String uid = json.getString("uid");
				if (StringUtils.isNotBlank(uid)) {// 如果uid存在
					// 获取第三方用户信息存放到session中
					ThirdPartyUser thirdUser = ThirdPartyLoginHelper.getSinaUserinfo(json.getString("access_token"), uid);
					String provider = "SINA";
					// 查询是否已经绑定过
					String userId = sysUserService.queryUserIdByThirdParty(uid, provider);
					if (StringUtils.isBlank(userId)) { // 如果未绑定，创建临时账号并绑定user数据
						thirdUser.setProvider(provider);
						sysUserService.insertThirdPartyUser(thirdUser);
					}
					// 跳转到登录成功界面
					modelMap.put("retUrl", Resources.THIRDPARTY.getString("third_login_success"));
				} else {// 如果未获取到OpenID
						// 跳转到登录成功界面
					modelMap.put("retUrl", "-1");
				}
			} else {// 如果没有返回令牌，则直接返回到登录页面
					// 跳转到登录成功界面
				modelMap.put("retUrl", "-1");
			}
		} catch (Exception e) {
			// 跳转到登录失败界面
			modelMap.put("retUrl", "-1");
			e.printStackTrace();
		}

		return "/sns/redirect";
	}

	private String getRedirectUrl(String type, HttpServletRequest request) {
		String url = "";
		String host = request.getHeader("host");
		url = Resources.THIRDPARTY.getString("authorizeURL_" + type);
		if ("wx".equals(type)) {
			url = url + "?appid=" + Resources.THIRDPARTY.getString("app_id_" + type) + "&redirect_uri=http://" + host
					+ Resources.THIRDPARTY.getString("redirect_url_" + type) + "&response_type=code&scope="
					+ Resources.THIRDPARTY.getString("scope_" + type) + "&state=fhmj";
		} else {
			url = url + "?client_id=" + Resources.THIRDPARTY.getString("app_id_" + type) + "&response_type=code&scope="
					+ Resources.THIRDPARTY.getString("scope_" + type) + "&redirect_uri=http://" + host
					+ Resources.THIRDPARTY.getString("redirect_url_" + type);
		}
		return url;
	}
}
