package org.ibase4j.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.ibase4j.model.TMember;
import org.ibase4j.provider.IBizProvider;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.ibase4j.core.Constants.MSGCHKTYPE;
import top.ibase4j.core.base.Parameter;
import top.ibase4j.core.config.Resources;
import top.ibase4j.core.exception.LoginException;
import top.ibase4j.core.support.Assert;
import top.ibase4j.core.util.CacheUtil;
import top.ibase4j.core.util.DataUtil;
import top.ibase4j.core.util.InstanceUtil;
import top.ibase4j.core.util.PropertiesUtil;
import top.ibase4j.core.util.TokenUtil;
import top.ibase4j.core.util.WebUtil;
import top.ibase4j.model.Login;

/**
 * 用户登录
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:11:21
 */
@RestController
@RequestMapping("/app/")
@Api(value = "APP登录注册接口", description = "APP-登录注册接口")
public class LoginController extends AppBaseController<IBizProvider> {

    public String getService() {
        return "memberService";
    }

    @PostMapping("login.api")
    @ApiOperation(value = "APP会员登录", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object login(Login user, HttpServletRequest request, HttpServletResponse response) {
        String uuid = request.getHeader("UUID");
        org.springframework.util.Assert.notNull(uuid, "非法操作.");
        user = WebUtil.getParameter(request, Login.class);
        Assert.notNull(user.getAccount(), "ACCOUNT");
        Assert.notNull(user.getPassword(), "PASSWORD");

        String password = (String)CacheUtil.getCache().get(MSGCHKTYPE.LOGIN + user.getAccount());
        if (user.getPassword().equals(password)) { // 检查验证码
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("countSql", 0);
            params.put("enable", 1);
            params.put("loginKey", user.getAccount()); // 登录帐号/手机号/邮箱
            Parameter parameter = new Parameter(getService(), "query").setParam(params);
            Page<?> pageInfo = provider.execute(parameter).getResultPage();
            TMember member = null;
            if (pageInfo.getTotal() == 1) {
                member = (TMember)pageInfo.getRecords().get(0);
                //String oldUuid = StringUtils.defaultIfBlank(member.getUuid(), "");
                if (StringUtils.isNotBlank(member.getUuid())) {
                    TokenUtil.delToken(member.getUuid());
                }
                member.setIsOnline(1);
                member.setUuid(uuid);
                parameter = new Parameter(getService(), "update").setParam(member);
                provider.execute(parameter);

                try {
                    /* RongCloud rongCloud = RongCloud.getInstance(getSysParam("APP-KEY"), getSysParam("APP-SECRET"));
                     * Map<String, Object> extraMap = InstanceUtil.newHashMap();
                     * extraMap.put("type", SYSTEMPUBLISH.LGN);
                     * extraMap.put("memberId", member.getId());
                     * extraMap.put("oldUuid", oldUuid);
                     * extraMap.put("systemTime", DateUtil.getDateTime());
                     * String extra = JSON.toJSONString(extraMap);
                     * String msg = "帐号[" + user.getAccount() + "]在别的设备登录";
                     * CodeSuccessResult messageResult = rongCloud.message.publishSystem(Constants.SYSTEM_ID,
                     * new String[]{member.getId().toString()}, new TxtMessage(msg, extra), "登录通知", "登录通知", 0, 0);
                     * logger.info("== " + messageResult.toString()); */
                } catch (Exception e) {
                    logger.error("", e);
                }
            } else {
                TMember param = new TMember();
                param.setPhone(user.getAccount());
                param.setAvatar(PropertiesUtil.getString("ui.file.uri.prefix") + "extends/img/dftAvatar.png");
                param.setIsOnline(1);
                param.setUuid(uuid);
                parameter = new Parameter(getService(), "update").setParam(param);
                member = (TMember)provider.execute(parameter).getResult();
            }
            request.setAttribute("msg", "[" + user.getAccount() + "]登录成功.");
            ModelMap modelMap = new ModelMap();
            String token = member.getToken();
            modelMap.put("token", token);
            TokenUtil.setTokenInfo(uuid, member.getId().toString());
            Map<String, Object> result = InstanceUtil.newHashMap("token", token);
            result.put("userInfo", member);
            return setSuccessModelMap(modelMap, result);
        }
        request.setAttribute("msg", "[" + user.getAccount() + "]登录失败.");
        throw new LoginException(Resources.getMessage("LOGIN_FAIL"));
    }

    @PostMapping("logout.api")
    @ApiOperation(value = "APP会员登出", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object logout(HttpServletRequest request) {
        String token = request.getHeader("UUID");
        Assert.notNull(token, "ACCOUNT");
        if (StringUtils.isNotBlank(token)) {
            TokenUtil.delToken(token);
        }
        Long id = getCurrUser(request);
        if (DataUtil.isNotEmpty(id)) {
            TMember member = new TMember();
            member.setId(getCurrUser(request));
            member.setIsOnline(0);
            Parameter parameter = new Parameter(getService(), "update").setParam(member);
            provider.execute(parameter);
        }
        ModelMap modelMap = new ModelMap();
        return setSuccessModelMap(modelMap);
    }

    @PostMapping("chkAccount.api")
    @ApiOperation(value = "检查手机号/帐号/邮箱是否存在", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object chkAccount(Login user, HttpServletRequest request, HttpServletResponse response) {
        user = WebUtil.getParameter(request, Login.class);
        Assert.notNull(user.getAccount(), "ACCOUNT");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("countSql", 0);
        params.put("loginKey", user.getAccount()); // 登录帐号/手机号/邮箱
        Parameter parameter = new Parameter(getService(), "query").setParam(params);
        Page<?> pageInfo = provider.execute(parameter).getResultPage();

        ModelMap modelMap = new ModelMap();
        if (pageInfo.getTotal() > 1) {
            return setSuccessModelMap(modelMap, InstanceUtil.newHashMap("exists", 1));
        }
        return setSuccessModelMap(modelMap, InstanceUtil.newHashMap("exists", 0));
    }
}
