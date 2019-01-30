package org.ibase4j.web.sys;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.model.sys.SysMsg;
import org.ibase4j.service.sys.SysMsgService;
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
 * 短信  前端控制器
 * </p>
 *
 * @author ShenHuaJie
 * @since 2017-03-12
 */
@Controller
@RequestMapping("/msg")
public class SysMsgController extends BaseController<SysMsg, SysMsgService> {
    @ApiOperation(value = "查询短信")
    @RequiresPermissions("msg.list.read")
    @GetMapping(value = "/read/page")
    public Object query(HttpServletRequest request) {
        Map<String, Object> param = WebUtil.getParameter(request);
        return super.query(param);
    }

    @ApiOperation(value = "短信详情")
    @RequiresPermissions("msg.list.read")
    @GetMapping(value = "/read/detail")
    public Object get(SysMsg param) {
        return super.get(param);
    }

    @Override
    @PostMapping
    @ApiOperation(value = "修改短信")
    @RequiresPermissions("msg.list.update")
    public Object update(SysMsg param) {
        return super.update(param);
    }

    @Override
    @DeleteMapping
    @ApiOperation(value = "删除短信")
    @RequiresPermissions("msg.list.delete")
    public Object delete(SysMsg param) {
        return super.delete(param);
    }
}