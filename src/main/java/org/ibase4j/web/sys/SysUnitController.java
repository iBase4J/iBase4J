package org.ibase4j.web.sys;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.model.sys.SysUnit;
import org.ibase4j.service.sys.SysUnitService;
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
 * 单位管理控制类
 *
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:13:31
 */
@RestController
@Api(value = "单位管理", description = "单位管理")
@RequestMapping(value = "unit")
public class SysUnitController extends BaseController<SysUnit, SysUnitService> {
    @ApiOperation(value = "查询单位")
    @RequiresPermissions("sys.base.unit.read")
    @GetMapping(value = "/read/list")
    public Object queryList(HttpServletRequest request) {
        Map<String, Object> param = WebUtil.getParameter(request);
        return super.queryList(param);
    }

    @ApiOperation(value = "查询单位")
    @RequiresPermissions("sys.base.unit.read")
    @GetMapping(value = "/read/page")
    public Object query(HttpServletRequest request) {
        Map<String, Object> param = WebUtil.getParameter(request);
        return super.query(param);
    }

    @ApiOperation(value = "单位详情")
    @RequiresPermissions("sys.base.unit.read")
    @GetMapping(value = "/read/detail")
    public Object get(SysUnit param) {
        return super.get(param);
    }

    @Override
    @PostMapping
    @ApiOperation(value = "修改单位")
    @RequiresPermissions("sys.base.unit.update")
    public Object update(SysUnit param) {
        return super.update(param);
    }

    @Override
    @DeleteMapping
    @ApiOperation(value = "删除单位")
    @RequiresPermissions("sys.base.unit.delete")
    public Object delete(SysUnit param) {
        return super.delete(param);
    }
}
