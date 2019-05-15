package org.ibase4j.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.ibase4j.model.TMember;
import org.ibase4j.service.MemberService;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import top.ibase4j.core.Constants;
import top.ibase4j.core.Constants.MsgChkType;
import top.ibase4j.core.base.AppBaseController;
import top.ibase4j.core.exception.LoginException;
import top.ibase4j.core.support.Assert;
import top.ibase4j.core.support.http.SessionUser;
import top.ibase4j.core.support.security.coder.HmacCoder;
import top.ibase4j.core.util.CacheUtil;
import top.ibase4j.core.util.DataUtil;
import top.ibase4j.core.util.InstanceUtil;
import top.ibase4j.core.util.PropertiesUtil;
import top.ibase4j.core.util.SecurityUtil;

/**
 * 用户登录
 *
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:11:21
 */
@RestController
@RequestMapping("/app/")
@Api(value = "APP登录注册接口", description = "APP-登录注册接口")
public class LoginController extends AppBaseController<TMember, MemberService> {
    @PostMapping("reginit")
    @ApiOperation(value = "注册", produces = MediaType.APPLICATION_JSON_VALUE, notes = "使用手机号+密码+短信验证码进行注册", response = TMember.class)
    public Object register(@RequestParam @ApiParam(value = "手机号", required = true) String account,
        @RequestParam @ApiParam(value = "密码", required = true) String password,
        @RequestParam @ApiParam(value = "手机验证码", required = true) String authCode) throws Exception {
        String authCodeOnServer = (String)CacheUtil.getCache().get(MsgChkType.REGISTER + account);
        if (!authCode.equals(authCodeOnServer)) {
            logger.warn(account + "=" + authCode + "-" + authCodeOnServer);
            throw new IllegalArgumentException("手机验证码错误");
        }

        Map<String, Object> params = InstanceUtil.newHashMap("loginKey", account);
        List<TMember> members = service.queryList(params);
        TMember member = members.isEmpty() ? null : members.get(0);

        if (member == null) {
            TMember param = new TMember();
            param.setPhone(account);
            param.setPassword(SecurityUtil.encryptPassword(password));
            param.setAvatar(PropertiesUtil.getString("ui.file.uri.prefix") + "extends/img/dftAvatar.png");
            service.update(param);
            return setSuccessModelMap();
        } else {
            throw new IllegalArgumentException("手机号已注册.");
        }
    }

    @PostMapping("login")
    @ApiOperation(value = "登录", produces = MediaType.APPLICATION_JSON_VALUE, notes = "使用手机号+密码登录登录接口", response = TMember.class)
    public Object login(@RequestHeader(required = false) @ApiParam("微信ID，暂不支持") String openId,
        @RequestHeader(required = false) @ApiParam("极光推送ID，暂不支持") String registrationId,
        @RequestParam @ApiParam(value = "手机号", required = true) String account,
        @RequestParam @ApiParam(value = "密码", required = true) String password) {
        Map<String, Object> params = InstanceUtil.newHashMap("enable", 1);
        params.put("loginKey", account);
        List<TMember> members = service.queryList(params);
        TMember member = members.isEmpty() ? null : members.get(0);

        if (member == null) {
            throw new LoginException("手机号或密码错误.");
        } else {
            if (SecurityUtil.encryptPassword(password).equals(member.getPassword())) {
                // if (member.getRegistrationId() != null) {
                // if (registrationId != null && !registrationId.equals(member.getRegistrationId())) {
                // member.setRegistrationId(registrationId);
                // String content = "帐号[" + account + "]在别的设备登录";
                // HashMap<String, String> hashMap = new HashMap<String, String>();
                // hashMap.put("type", "3");
                // String equipment = member.getRegistrationId().substring(2, 3);
                // try {
                // if ("0".equals(equipment)) {
                // jpushHelper.sendNotificationAndroid("登录通知", content, hashMap,
                // member.getRegistrationId());
                // } else {
                // jpushHelper.sendNotificationIOS("登录通知", content, hashMap, member.getRegistrationId());
                // }
                // } catch (Exception e) {
                // logger.info(ExceptionUtil.getStackTraceAsString(e));
                // }
                // }
                // } else {
                // member.setRegistrationId(registrationId);
                // }
                // if (openId != null) {
                // member.setWxOpenId(openId);
                // }
                member.setIsOnline(1);

                service.update(member);

                String token = SecurityUtil.initHmacKey(HmacCoder.MD5);
                String tokenKey = SecurityUtil.encryptMd5(token);
                SessionUser user = new SessionUser(member.getId(), member.getNickName(), member.getAvatar(), false);
                CacheUtil.getCache().set(Constants.TOKEN_KEY + tokenKey, user,
                    PropertiesUtil.getInt("APP-TOKEN-EXPIRE", 60 * 60 * 24 * 5));
                member.setToken(token);
                member.setPassword(null);
                return setSuccessModelMap(member);
            } else {
                throw new LoginException("手机号或密码错误.");
            }
        }
    }

    @PostMapping("logout")
    @ApiOperation(value = "APP会员登出", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object logout(HttpServletRequest request) {
        String token = request.getHeader("token");
        Assert.notNull(token, "ACCOUNT");
        if (StringUtils.isNotBlank(token)) {
            String tokenKey = SecurityUtil.encryptMd5(token);
            CacheUtil.getCache().del(Constants.TOKEN_KEY + tokenKey);
        }
        Long id = getCurrUser(request);
        if (DataUtil.isNotEmpty(id)) {
            TMember member = new TMember();
            member.setId(getCurrUser(request));
            member.setIsOnline(0);
            service.update(member);
        }
        ModelMap modelMap = new ModelMap();
        return setSuccessModelMap(modelMap);
    }

    @PostMapping("updatePwd")
    @ApiOperation(value = "修改密码", produces = MediaType.APPLICATION_JSON_VALUE, notes = "", response = TMember.class)
    public Object updatePwd(@RequestParam @ApiParam(value = "手机号", required = true) String account,
        @RequestParam @ApiParam(value = "密码", required = true) String password,
        @RequestParam @ApiParam(value = "手机验证码", required = true) String authCode) throws Exception {
        String authCodeOnServer = (String)CacheUtil.getCache().get(MsgChkType.CHGPWD + account);
        if (!authCode.equals(authCodeOnServer)) {
            throw new IllegalArgumentException("手机验证码错误");
        }

        Map<String, Object> params = InstanceUtil.newHashMap("loginKey", account);
        List<?> members = service.queryList(params);
        TMember member = members.isEmpty() ? null : (TMember)members.get(0);

        if (member == null) {
            throw new IllegalArgumentException("手机号还没有注册.");
        } else {
            member.setPassword(SecurityUtil.encryptPassword(password));
            service.update(member);
            return setSuccessModelMap();
        }
    }
}
