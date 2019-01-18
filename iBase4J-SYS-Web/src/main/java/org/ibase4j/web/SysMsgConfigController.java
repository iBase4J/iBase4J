package org.ibase4j.web;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.model.SysMsgConfig;
import org.ibase4j.service.SysMsgConfigService;
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
 *   前端控制器
 * </p>
 *
 * @author ShenHuaJie
 * @since 2017-03-12
 */
@RestController
@RequestMapping("/msgConfig")
public class SysMsgConfigController extends BaseController<SysMsgConfig, SysMsgConfigService> {
    @Override
    @ApiOperation(value = "查询")
    @RequiresPermissions("msg.config.read")
    @GetMapping(value = "/read/page")
    public Object query(ModelMap modelMap,  Map<String, Object> param) {
        return super.query(modelMap, param);
    }

    @ApiOperation(value = "详情")
    @RequiresPermissions("msg.config.read")
    @GetMapping(value = "/read/detail")
    public Object get(ModelMap modelMap,  SysMsgConfig param) {
        return super.get(modelMap, param);
    }

    @Override
    @PostMapping
    @ApiOperation(value = "修改")
    @RequiresPermissions("msg.config.update")
    public Object update(ModelMap modelMap,  SysMsgConfig param) {
        return super.update(modelMap, param);
    }

    @Override
    @DeleteMapping
    @ApiOperation(value = "删除")
    @RequiresPermissions("msg.config.delete")
    public Object delete(ModelMap modelMap,  SysMsgConfig param) {
        return super.delete(modelMap, param);
    }
}
