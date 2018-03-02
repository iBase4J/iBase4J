package org.ibase4j.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.ibase4j.bean.Member;
import org.ibase4j.model.MemberPhoto;
import org.ibase4j.model.TMember;
import org.ibase4j.provider.IBizProvider;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.ibase4j.core.base.provider.AppBaseController;
import top.ibase4j.core.base.provider.Parameter;
import top.ibase4j.core.support.Assert;
import top.ibase4j.core.util.CacheUtil;
import top.ibase4j.core.util.DataUtil;
import top.ibase4j.core.util.InstanceUtil;
import top.ibase4j.core.util.SecurityUtil;
import top.ibase4j.core.util.UploadUtil;
import top.ibase4j.core.util.WebUtil;
import top.ibase4j.model.Login;

/**
 * <p>
 * 会员 前端控制器
 * </p>
 *
 * @author ShenHuaJie
 * @since 2017-03-19
 */
@Controller
@RequestMapping("/app/member/")
@Api(value = "会员管理接口", description = "APP-个人中心-个人信息管理接口")
public class TMemberController extends AppBaseController<IBizProvider> {
    public String getService() {
        return "memberService";
    }

    @ApiOperation(value = "获取个人基本信息", produces = MediaType.APPLICATION_JSON_VALUE, response = TMember.class)
    @RequestMapping(value = "getUserBaseInfo.api", method = {RequestMethod.GET, RequestMethod.POST})
    public Object getBaseInfo(HttpServletRequest request, String id) {
        Member param = WebUtil.getParameter(request, Member.class);
        Assert.notNull(param.getId(), "ID");
        Parameter parameter = new Parameter(getService(), "getBaseInfo", param.getId());
        Object result = provider.execute(parameter).getResult();
        ModelMap modelMap = new ModelMap();
        return setSuccessModelMap(modelMap, result);
    }

    @ApiOperation(value = "获取个人信息", produces = MediaType.APPLICATION_JSON_VALUE, response = TMember.class)
    @RequestMapping(value = "getUserInfo.api", method = {RequestMethod.GET, RequestMethod.POST})
    public Object get(HttpServletRequest request, String id) {
        Member param = WebUtil.getParameter(request, Member.class);
        Long memberId = getCurrUser(request);
        if (DataUtil.isNotEmpty(memberId)) {
            param.setId(memberId);
        }
        Assert.notNull(param.getId(), "ID");
        Parameter parameter = new Parameter(getService(), "getInfo", param.getId());
        logger.info("{} execute queryById start...", parameter.getNo());
        TMember result = (TMember)provider.execute(parameter).getResult();
        result.setPassword(null);
        ModelMap modelMap = new ModelMap();
        logger.info("{} execute queryById end.", parameter.getNo());
        return setSuccessModelMap(modelMap, result);
    }

    @PostMapping("modifyUserInfo.api")
    @ApiOperation(value = "修改个人信息", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object update(HttpServletRequest request, Member member) {
        TMember param = WebUtil.getParameter(request, TMember.class);
        Long id = getCurrUser(request);
        if (DataUtil.isNotEmpty(id)) {
            member.setId(id);
        }
        Assert.notNull(param.getId(), "ID");
        Parameter parameter = new Parameter(getService(), "queryById").setParam(param.getId());
        TMember user = (TMember)provider.execute(parameter).getResult();
        Assert.notNull(user, "MEMBER", param.getId());
        if (StringUtils.isNotBlank(param.getPassword())) {
            if (!param.getPassword().equals(user.getPassword())) {
                param.setPassword(SecurityUtil.encryptPassword(param.getPassword()));
            }
        }
        ModelMap modelMap = new ModelMap();
        return super.update(request, modelMap, param);
    }

    @PostMapping("uploadPhoto.api")
    @ApiOperation(value = "修改个人头像", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object uploadPhoto(HttpServletRequest request, MemberPhoto param) {
        Long id = getCurrUser(request);
        if (DataUtil.isNotEmpty(id)) {
            param.setMemberId(id);
        }
        Assert.notNull(param.getMemberId(), "ID");
        List<String> avatars = UploadUtil.uploadImage(request, false);
        org.springframework.util.Assert.notEmpty(avatars, "头像数据dataFile不能为空");
        TMember member = new TMember();
        member.setId(param.getMemberId());
        Parameter parameter = new Parameter(getService(), "queryById", member.getId());
        TMember user = (TMember)provider.execute(parameter).getResult();
        Assert.notNull(user, "MEMBER", member.getId());
        String filePath = UploadUtil.getUploadDir(request) + avatars.get(0);
        String avatar = UploadUtil.remove2DFS("member", "M" + member.getId(), filePath).getRemotePath();
        member.setAvatar(avatar);
        Long userId = getCurrUser(request);
        member.setUpdateBy(userId);
        member.setUpdateTime(new Date());
        parameter = new Parameter(getService(), "update", member);
        logger.info("{} execute update start...", parameter.getNo());
        provider.execute(parameter);
        logger.info("{} execute update end.", parameter.getNo());
        Map<String, Object> result = InstanceUtil.newHashMap("bizeCode", 1);
        result.put("avatar", avatar);
        return setSuccessModelMap(new ModelMap(), result);
    }

    @PostMapping("updatePhoneByIdCard.api")
    @ApiOperation(value = "修改个人手机号", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object updatePhone(HttpServletRequest request, String newPhone, String orderPhone, String idCard,
        String realname) {
        Map<String, Object> parame = WebUtil.getParameter(request);
        Parameter parameter = new Parameter(getService(), "updataphone", parame);
        Object result = provider.execute(parameter).getResult();
        return setSuccessModelMap(new ModelMap(), result);
    }

    @PostMapping("updatePhoneByPhone.api")
    @ApiOperation(value = "修改个人手机号", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object updatePhone(HttpServletRequest request, Login user, String memberId) {
        Assert.notNull(user.getAccount(), "ACCOUNT");
        Assert.notNull(user.getPassword(), "PASSWORD");
        String password = (String)CacheUtil.getCache().get("CHGINFO_" + user.getAccount());
        if (user.getPassword().equals(password)) {
            TMember tMember = new TMember();
            tMember.setPhone(user.getAccount());
            tMember.setId(Long.parseLong(memberId));
            return super.update(request, new ModelMap(), tMember);
        }
        return setSuccessModelMap(new ModelMap(), "验证码错误");
    }

    @ApiOperation("实名认证")
    @PostMapping("/authentication.api")
    public Object authentication(HttpServletRequest request, String memberId, String realName, String idCard) {
        Map<String, Object> parame = WebUtil.getParameter(request);
        Parameter parameter = new Parameter(getService(), "authentication", parame);
        Object result = provider.execute(parameter).getResult();
        return setSuccessModelMap(new ModelMap(), result);
    }
}
