package org.ibase4j.web.sys;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.model.sys.SysArticle;
import org.ibase4j.service.sys.SysArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.ApiOperation;
import top.ibase4j.core.base.BaseController;
import top.ibase4j.core.util.WebUtil;

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
    @ApiOperation(value = "查询文章")
    @RequiresPermissions("cms.article.read")
    @GetMapping(value = "/read/page")
    public Object query(HttpServletRequest request) {
        Map<String, Object> param = WebUtil.getParameter(request);
        return super.query(param);
    }

    @ApiOperation(value = "文章详情")
    @RequiresPermissions("cms.article.read")
    @GetMapping(value = "/read/detail")
    public Object get(SysArticle param) {
        return super.get(param);
    }

    @Override
    @PostMapping
    @ApiOperation(value = "修改文章")
    @RequiresPermissions("cms.article.update")
    public Object update(SysArticle param) {
        if (param.getEnable() == null) {
            param.setEnable(0);
        }
        if (param.getIsTop() == null) {
            param.setIsTop(0);
        }
        return super.update(param);
    }

    @Override
    @DeleteMapping
    @ApiOperation(value = "删除文章")
    @RequiresPermissions("cms.article.delete")
    public Object delete(SysArticle param) {
        return super.delete(param);
    }
}
