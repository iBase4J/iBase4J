package org.ibase4j.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.model.SysEmailConfig;
import org.ibase4j.service.SysEmailConfigService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.ibase4j.core.base.BaseController;
import top.ibase4j.core.util.DataUtil;
import top.ibase4j.core.util.SecurityUtil;
import top.ibase4j.core.util.WebUtil;

/**
 * 邮件配置管理控制类
 *
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:13:31
 */
@RestController
@Api(value = "邮件配置管理", description = "邮件配置管理")
@RequestMapping(value = "emailConfig")
public class SysEmailConfigController extends BaseController<SysEmailConfig, SysEmailConfigService> {
    @ApiOperation(value = "查询邮件配置")
    @RequiresPermissions("sys.email.config.read")
    @GetMapping("/read/page")
    public Object query(HttpServletRequest request) {
        Map<String, Object> param = WebUtil.getParameter(request);
        return super.query(param);
    }

    @ApiOperation(value = "邮件配置详情")
    @RequiresPermissions("sys.email.config.read")
    @GetMapping("/read/detail")
    public Object get(SysEmailConfig param) {
        return super.get(param);
    }

    @Override
    @ApiOperation(value = "修改邮件配置")
    @RequiresPermissions("sys.email.config.update")
    @RequestMapping(method = RequestMethod.POST)
    public Object update(SysEmailConfig param) {
        if (param.getId() != null) {
            SysEmailConfig result = service.queryById(param.getId());
            if (param.getSenderPassword() != null && !param.getSenderPassword().equals(result.getSenderPassword())) {
                param.setSenderPassword(SecurityUtil.encryptMd5(param.getSenderPassword()));
            }
        } else if (DataUtil.isNotEmpty(param.getSenderPassword())) {
            param.setSenderPassword(SecurityUtil.encryptMd5(param.getSenderPassword()));
        }
        return super.update(param);
    }

    @Override
    @ApiOperation(value = "删除邮件配置")
    @RequiresPermissions("sys.email.config.delete")
    @RequestMapping(method = RequestMethod.DELETE)
    public Object delete(SysEmailConfig param) {
        return super.delete(param);
    }
}
