package org.ibase4j.web;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.model.SysFeedback;
import org.ibase4j.service.SysFeedbackService;
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
 * 反馈  前端控制器
 * </p>
 *
 * @author ShenHuaJie
 * @since 2017-03-12
 */
@RestController
@RequestMapping("/feedback")
public class SysFeedbackController extends BaseController<SysFeedback, SysFeedbackService> {
    @Override
    @ApiOperation(value = "查询反馈")
    @RequiresPermissions("cms.feedback.read")
    @GetMapping(value = "/read/page")
    public Object query(ModelMap modelMap,  Map<String, Object> param) {
        return super.query(modelMap, param);
    }

    @ApiOperation(value = "反馈详情")
    @RequiresPermissions("cms.feedback.read")
    @GetMapping(value = "/read/detail")
    public Object get(ModelMap modelMap,  SysFeedback param) {
        return super.get(modelMap, param);
    }

    @Override
    @PostMapping
    @ApiOperation(value = "修改反馈")
    @RequiresPermissions("cms.feedback.update")
    public Object update(ModelMap modelMap,  SysFeedback param) {
        return super.update(modelMap, param);
    }

    @Override
    @DeleteMapping
    @ApiOperation(value = "删除反馈")
    @RequiresPermissions("cms.feedback.delete")
    public Object delete(ModelMap modelMap,  SysFeedback param) {
        return super.delete(modelMap, param);
    }
}