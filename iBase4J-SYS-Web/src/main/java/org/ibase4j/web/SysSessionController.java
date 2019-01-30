package org.ibase4j.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.model.SysSession;
import org.ibase4j.service.SysSessionService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.ibase4j.core.base.BaseController;
import top.ibase4j.core.listener.SessionListener;
import top.ibase4j.core.util.WebUtil;

/**
 * 用户会话管理
 *
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:13:06
 */
@RestController
@Api(value = "会话管理", description = "会话管理")
@RequestMapping(value = "/session")
public class SysSessionController extends BaseController<SysSession, SysSessionService> {
    @Resource
    private SessionListener sessionListener;

    // 查询会话
    @ApiOperation(value = "查询会话")
    @GetMapping(value = "/read/page")
    @RequiresPermissions("sys.base.session.read")
    public Object get(ModelMap modelMap, HttpServletRequest request) {
        Map<String, Object> param = WebUtil.getParameter(request);
        Integer number = sessionListener.getAllUserNumber();
        modelMap.put("userNumber", number); // 用户数大于会话数,有用户没有登录
        return super.query(modelMap, param);
    }

    @Override
    @DeleteMapping
    @ApiOperation(value = "删除会话")
    @RequiresPermissions("sys.base.session.delete")
    public Object delete(SysSession param) {
        return super.delete(param);
    }
}
