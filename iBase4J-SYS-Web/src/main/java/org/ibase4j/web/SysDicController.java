package org.ibase4j.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.model.SysDic;
import org.ibase4j.service.SysDicService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.ibase4j.core.base.BaseController;
import top.ibase4j.core.exception.BusinessException;
import top.ibase4j.core.util.WebUtil;

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
    @ApiOperation(value = "查询字典项")
    @RequiresPermissions("sys.base.dic.read")
    @GetMapping(value = "/read/page")
    public Object query(HttpServletRequest request) {
        Map<String, Object> param = WebUtil.getParameter(request);
        param.put("orderBy", "type_,sort_no");
        return super.query(param);
    }

    @ApiOperation(value = "查询字典项")
    @RequiresPermissions("sys.base.dic.read")
    @GetMapping(value = "/read/list")
    public Object queryList(HttpServletRequest request) {
        Map<String, Object> param = WebUtil.getParameter(request);
        param.put("orderBy", "type_,sort_no");
        return super.queryList(param);
    }

    @ApiOperation(value = "字典项详情")
    @RequiresPermissions("sys.base.dic.read")
    @GetMapping(value = "/read/detail")
    public Object get(SysDic param) {
        return super.get(param);
    }

    @Override
    @PostMapping
    @ApiOperation(value = "修改字典项")
    @RequiresPermissions("sys.base.dic.update")
    public Object update(SysDic param) {
        if (param.getId() != null) {
            SysDic result = service.queryById(param.getId());
            if ("0".equals(result.getEditable())) {
                throw new BusinessException("不允许修改系统内置字典项");
            }
        }
        return super.update(param);
    }

    @Override
    @DeleteMapping
    @ApiOperation(value = "删除字典项")
    @RequiresPermissions("sys.base.dic.delete")
    public Object delete(SysDic param) {
        return super.delete(param);
    }
}
