package org.ibase4j.web.sys;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.model.sys.SysNews;
import org.ibase4j.service.sys.SysNewsService;
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
 * 新闻管理控制类
 *
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:13:31
 */
@RestController
@Api(value = "新闻管理", description = "新闻管理")
@RequestMapping(value = "news")
public class SysNewsController extends BaseController<SysNews, SysNewsService> {
    @Override
    @ApiOperation(value = "查询新闻")
    @RequiresPermissions("sys.cms.news.read")
    @PutMapping(value = "/read/page")
    public Object query(ModelMap modelMap, @RequestBody Map<String, Object> param) {
        return super.query(modelMap, param);
    }

    @ApiOperation(value = "新闻详情")
    @RequiresPermissions("sys.cms.news.read")
    @PutMapping(value = "/read/detail")
    public Object get(ModelMap modelMap, @RequestBody SysNews param) {
        return super.get(modelMap, param);
    }

    @Override
    @PostMapping
    @ApiOperation(value = "修改新闻")
    @RequiresPermissions("sys.cms.news.update")
    public Object update(ModelMap modelMap, @RequestBody SysNews param) {
        if (param.getStatus() == null) {
            param.setStatus("0");
        }
        return super.update(modelMap, param);
    }

    @Override
    @DeleteMapping
    @ApiOperation(value = "删除新闻")
    @RequiresPermissions("sys.cms.news.delete")
    public Object delete(ModelMap modelMap, @RequestBody SysNews param) {
        return super.delete(modelMap, param);
    }
}
