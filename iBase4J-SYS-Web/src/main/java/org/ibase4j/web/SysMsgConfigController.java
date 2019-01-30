package org.ibase4j.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.model.SysMsgConfig;
import org.ibase4j.service.SysMsgConfigService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.ApiOperation;
import top.ibase4j.core.base.BaseController;
import top.ibase4j.core.util.WebUtil;

/**
 * <p>
 *   前端控制器
 * </p>
 *
 * @author ShenHuaJie
 * @since 2017-03-12
 */
@Controller
@RequestMapping("/msgConfig")
public class SysMsgConfigController extends BaseController<SysMsgConfig, SysMsgConfigService> {
    @ApiOperation(value = "查询")
    @RequiresPermissions("msg.config.read")
    @GetMapping(value = "/read/page")
    public Object query(HttpServletRequest request) {
        Map<String, Object> param = WebUtil.getParameter(request);
        return super.query(param);
    }

    @ApiOperation(value = "详情")
    @RequiresPermissions("msg.config.read")
    @GetMapping(value = "/read/detail")
    public Object get(SysMsgConfig param) {
        return super.get(param);
    }

    @Override
    @PostMapping
    @ApiOperation(value = "修改")
    @RequiresPermissions("msg.config.update")
    public Object update(SysMsgConfig param) {
        return super.update(param);
    }

    @Override
    @DeleteMapping
    @ApiOperation(value = "删除")
    @RequiresPermissions("msg.config.delete")
    public Object delete(SysMsgConfig param) {
        return super.delete(param);
    }
}
