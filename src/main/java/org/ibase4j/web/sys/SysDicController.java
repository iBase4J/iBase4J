package org.ibase4j.web.sys;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.model.sys.SysDic;
import org.ibase4j.service.sys.SysDicService;
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
 * 字典管理
 *
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:14:34
 */
@RestController
@Api(value = "字典管理", description = "字典管理")
@RequestMapping(value = "/dic")
public class SysDicController extends BaseController<SysDic, SysDicService> {
    @Override
    @ApiOperation(value = "查询字典项")
    @RequiresPermissions("sys.base.dic.read")
    @GetMapping(value = "/read/page")
    public Object query(ModelMap modelMap, Map<String, Object> param) {
        param.put("orderBy", "type_,sort_no");
        return super.query(modelMap, param);
    }

    @Override
    @ApiOperation(value = "查询字典项")
    @RequiresPermissions("sys.base.dic.read")
    @GetMapping(value = "/read/list")
    public Object queryList(ModelMap modelMap, Map<String, Object> param) {
        param.put("orderBy", "type_,sort_no");
        return super.queryList(modelMap, param);
    }

    @ApiOperation(value = "字典项详情")
    @RequiresPermissions("sys.base.dic.read")
    @GetMapping(value = "/read/detail")
    public Object get(ModelMap modelMap, SysDic param) {
        return super.get(modelMap, param);
    }

    @Override
    @PostMapping
    @ApiOperation(value = "修改字典项")
    @RequiresPermissions("sys.base.dic.update")
    public Object update(ModelMap modelMap, SysDic param) {
        return super.update(modelMap, param);
    }

    @Override
    @DeleteMapping
    @ApiOperation(value = "删除字典项")
    @RequiresPermissions("sys.base.dic.delete")
    public Object delete(ModelMap modelMap, SysDic param) {
        return super.delete(modelMap, param);
    }
}
