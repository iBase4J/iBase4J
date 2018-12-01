package org.ibase4j.web;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.model.SysParam;
import org.ibase4j.service.SysParamService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.ibase4j.core.base.BaseController;

/**
 * 参数管理
 *
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:15:19
 */
@RestController
@Api(value = "系统参数管理", description = "系统参数管理")
@RequestMapping(value = "param")
public class SysParamController extends BaseController<SysParam, SysParamService> {
    @Override
    @PutMapping(value = "/read/page")
    @ApiOperation(value = "查询系统参数")
    @RequiresPermissions("sys.base.param.read")
    public Object query(ModelMap modelMap, @RequestBody Map<String, Object> param) {
        return super.query(modelMap, param);
    }

    @PutMapping(value = "/read/detail")
    @ApiOperation(value = "系统参数详情")
    @RequiresPermissions("sys.base.param.read")
    public Object get(ModelMap modelMap, @RequestBody SysParam param) {
        return super.get(modelMap, param);
    }

    @Override
    @PostMapping
    @ApiOperation(value = "修改系统参数")
    @RequiresPermissions("sys.base.param.update")
    public Object update(ModelMap modelMap, @RequestBody SysParam param) {
        return super.update(modelMap, param);
    }

    @Override
    @DeleteMapping
    @ApiOperation(value = "删除系统参数")
    @RequiresPermissions("sys.base.param.delete")
    public Object delete(ModelMap modelMap, @RequestBody SysParam param) {
        return super.delete(modelMap, param);
    }
}
