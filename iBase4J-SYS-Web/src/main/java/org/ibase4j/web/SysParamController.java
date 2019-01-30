package org.ibase4j.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.model.SysParam;
import org.ibase4j.service.SysParamService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.ibase4j.core.base.BaseController;
import top.ibase4j.core.util.WebUtil;

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
    @GetMapping(value = "/read/page")
    @ApiOperation(value = "查询系统参数")
    @RequiresPermissions("sys.base.param.read")
    public Object query(HttpServletRequest request) {
        Map<String, Object> param = WebUtil.getParameter(request);
        return super.query(param);
    }

    @GetMapping(value = "/read/detail")
    @ApiOperation(value = "系统参数详情")
    @RequiresPermissions("sys.base.param.read")
    public Object get(SysParam param) {
        return super.get(param);
    }

    @Override
    @PostMapping
    @ApiOperation(value = "修改系统参数")
    @RequiresPermissions("sys.base.param.update")
    public Object update(SysParam param) {
        return super.update(param);
    }

    @Override
    @DeleteMapping
    @ApiOperation(value = "删除系统参数")
    @RequiresPermissions("sys.base.param.delete")
    public Object delete(SysParam param) {
        return super.delete(param);
    }
}
