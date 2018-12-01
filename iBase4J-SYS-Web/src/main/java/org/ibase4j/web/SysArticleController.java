package org.ibase4j.web;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.model.SysArticle;
import org.ibase4j.service.SysArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.ApiOperation;
import top.ibase4j.core.base.BaseController;

/**
 * <p>
 * 文章  前端控制器
 * </p>
 *
 * @author ShenHuaJie
 * @since 2017-03-12
 */
@Controller
@RequestMapping("/article")
public class SysArticleController extends BaseController<SysArticle, SysArticleService> {
    @Override
    @ApiOperation(value = "查询文章")
    @RequiresPermissions("cms.article.read")
    @PutMapping(value = "/read/page")
    public Object query(ModelMap modelMap, @RequestBody Map<String, Object> param) {
        return super.query(modelMap, param);
    }

    @ApiOperation(value = "文章详情")
    @RequiresPermissions("cms.article.read")
    @PutMapping(value = "/read/detail")
    public Object get(ModelMap modelMap, @RequestBody SysArticle param) {
        return super.get(modelMap, param);
    }

    @Override
    @PostMapping
    @ApiOperation(value = "修改文章")
    @RequiresPermissions("cms.article.update")
    public Object update(ModelMap modelMap, @RequestBody SysArticle param) {
        if (param.getEnable() == null) {
            param.setEnable(0);
        }
        if (param.getIsTop() == null) {
            param.setIsTop(0);
        }
        return super.update(modelMap, param);
    }

    @Override
    @DeleteMapping
    @ApiOperation(value = "删除文章")
    @RequiresPermissions("cms.article.delete")
    public Object delete(ModelMap modelMap, @RequestBody SysArticle param) {
        return super.delete(modelMap, param);
    }
}
