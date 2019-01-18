package org.ibase4j.web.sys;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.model.sys.SysEmail;
import org.ibase4j.service.sys.SysEmailService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.ibase4j.core.base.BaseController;

/**
 * 邮件管理控制类
 *
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:13:31
 */
@RestController
@Api(value = "邮件管理", description = "邮件管理")
@RequestMapping(value = "email")
public class SysEmailController extends BaseController<SysEmail, SysEmailService> {
    @Override
    @ApiOperation(value = "查询邮件")
    @RequiresPermissions("sys.email.list.read")
    @GetMapping(value = "/read/page")
    public Object query(ModelMap modelMap, Map<String, Object> param) {
        return super.query(modelMap, param);
    }

    @ApiOperation(value = "邮件详情")
    @RequiresPermissions("sys.email.list.read")
    @GetMapping(value = "/read/detail")
    public Object get(ModelMap modelMap, SysEmail param) {
        return super.get(modelMap, param);
    }

    @Override
    @PostMapping
    @ApiOperation(value = "修改邮件")
    @RequiresPermissions("sys.email.list.update")
    public Object update(ModelMap modelMap, SysEmail param) {
        return super.update(modelMap, param);
    }

    @Override
    @DeleteMapping
    @ApiOperation(value = "删除邮件")
    @RequiresPermissions("sys.email.list.delete")
    public Object delete(ModelMap modelMap, SysEmail param) {
        return super.delete(modelMap, param);
    }
}
