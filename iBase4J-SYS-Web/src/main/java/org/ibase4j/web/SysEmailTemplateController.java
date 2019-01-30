package org.ibase4j.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.model.SysEmailTemplate;
import org.ibase4j.service.SysEmailTemplateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.ibase4j.core.base.BaseController;
import top.ibase4j.core.util.WebUtil;

/**
 * 邮件模版管理控制类
 *
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:13:31
 */
@RestController
@Api(value = "邮件模版管理", description = "邮件模版管理")
@RequestMapping(value = "emailTemplate")
public class SysEmailTemplateController extends BaseController<SysEmailTemplate, SysEmailTemplateService> {
    @ApiOperation(value = "查询邮件模版")
    @RequiresPermissions("sys.email.template.read")
    @GetMapping("/read/page")
    public Object query(HttpServletRequest request) {
        Map<String, Object> param = WebUtil.getParameter(request);
        return super.query(param);
    }

    @ApiOperation(value = "邮件模版详情")
    @RequiresPermissions("sys.email.template.read")
    @GetMapping("/read/detail")
    public Object get(SysEmailTemplate param) {
        return super.get(param);
    }

    @Override
    @ApiOperation(value = "修改邮件模版")
    @RequiresPermissions("sys.email.template.update")
    @RequestMapping(method = RequestMethod.POST)
    public Object update(SysEmailTemplate param) {
        return super.update(param);
    }

    @Override
    @ApiOperation(value = "删除邮件模版")
    @RequiresPermissions("sys.email.template.delete")
    @RequestMapping(method = RequestMethod.DELETE)
    public Object delete(SysEmailTemplate param) {
        return super.delete(param);
    }
}
