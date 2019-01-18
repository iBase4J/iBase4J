package org.ibase4j.web;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.ibase4j.core.base.BaseController;
import top.ibase4j.model.SysEvent;
import top.ibase4j.service.SysEventService;

/**
 * 系统日志控制类
 *
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:13:31
 */
@RestController
@Api(value = "系统日志", description = "系统日志")
@RequestMapping(value = "event")
public class SysEventController extends BaseController<SysEvent, SysEventService> {
    @Override
    @ApiOperation(value = "查询系统日志")
    @RequiresPermissions("sys.base.event.read")
    @GetMapping(value = "/read/page")
    public Object query(ModelMap modelMap,  Map<String, Object> param) {
        return super.query(modelMap, param);
    }
}
