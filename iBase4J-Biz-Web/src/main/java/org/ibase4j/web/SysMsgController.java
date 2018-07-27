package org.ibase4j.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.ibase4j.model.SendMsg;
import org.ibase4j.service.SendMsgService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.ibase4j.core.base.AbstractController;
import top.ibase4j.core.util.WebUtil;

/**
 * <p>
 * 短信 前端控制器
 * </p>
 *
 * @author ShenHuaJie
 * @since 2017-03-12
 */
@Controller
@RequestMapping("/app/")
@Api(value = "APP短信接口", description = "APP-短信接口")
public class SysMsgController extends AbstractController {
    @Resource
    private SendMsgService sendMsgService;

    @PostMapping("msg.api")
    @ApiOperation(value = "发送短信", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object update(HttpServletRequest request, SendMsg record) {
        record = WebUtil.getParameter(request, SendMsg.class);
        Assert.notNull(record.getMsgType(), "短信类型不能为空.");
        Assert.notNull(record.getPhone(), "手机号不能为空.");
        sendMsgService.sendMsg(record);
        ModelMap modelMap = new ModelMap();
        return setSuccessModelMap(modelMap);
    }
}
