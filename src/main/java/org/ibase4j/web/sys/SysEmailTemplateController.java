package org.ibase4j.web.sys;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.model.sys.SysEmailTemplate;
import org.ibase4j.service.sys.SysEmailTemplateService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.ibase4j.core.base.BaseController;

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
    @Override
    @ApiOperation(value = "查询邮件模版")
    @RequiresPermissions("sys.email.template.read")
    @RequestMapping(value = "/read/page", method = RequestMethod.PUT)
    public Object query(ModelMap modelMap, @RequestBody Map<String, Object> param) {
        return super.query(modelMap, param);
    }

    @ApiOperation(value = "邮件模版详情")
    @RequiresPermissions("sys.email.template.read")
    @RequestMapping(value = "/read/detail", method = RequestMethod.PUT)
    public Object get(ModelMap modelMap, @RequestBody SysEmailTemplate param) {
        return super.get(modelMap, param);
    }

    @Override
    @ApiOperation(value = "修改邮件模版")
    @RequiresPermissions("sys.email.template.update")
    @RequestMapping(method = RequestMethod.POST)
    public Object update(ModelMap modelMap, @RequestBody SysEmailTemplate param) {
        return super.update(modelMap, param);
    }

    @Override
    @ApiOperation(value = "删除邮件模版")
    @RequiresPermissions("sys.email.template.delete")
    @RequestMapping(method = RequestMethod.DELETE)
    public Object delete(ModelMap modelMap, @RequestBody SysEmailTemplate param) {
        return super.delete(modelMap, param);
    }
}
