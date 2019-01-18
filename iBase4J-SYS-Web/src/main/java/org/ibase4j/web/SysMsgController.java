package org.ibase4j.web;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.model.SysMsg;
import org.ibase4j.service.SysMsgService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import top.ibase4j.core.base.BaseController;

/**
 * <p>
 * 短信  前端控制器
 * </p>
 *
 * @author ShenHuaJie
 * @since 2017-03-12
 */
@RestController
@RequestMapping("/msg")
public class SysMsgController extends BaseController<SysMsg, SysMsgService> {
    @Override
    @ApiOperation(value = "查询短信")
    @RequiresPermissions("msg.list.read")
    @GetMapping(value = "/read/page")
    public Object query(ModelMap modelMap,  Map<String, Object> param) {
        return super.query(modelMap, param);
    }

    @ApiOperation(value = "短信详情")
    @RequiresPermissions("msg.list.read")
    @GetMapping(value = "/read/detail")
    public Object get(ModelMap modelMap,  SysMsg param) {
        return super.get(modelMap, param);
    }

    @Override
    @PostMapping
    @ApiOperation(value = "修改短信")
    @RequiresPermissions("msg.list.update")
    public Object update(ModelMap modelMap,  SysMsg param) {
        return super.update(modelMap, param);
    }

    @Override
    @DeleteMapping
    @ApiOperation(value = "删除短信")
    @RequiresPermissions("msg.list.delete")
    public Object delete(ModelMap modelMap,  SysMsg param) {
        return super.delete(modelMap, param);
    }
}