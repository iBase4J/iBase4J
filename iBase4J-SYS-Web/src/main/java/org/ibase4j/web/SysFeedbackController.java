package org.ibase4j.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.model.SysFeedback;
import org.ibase4j.service.SysFeedbackService;
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
 * 反馈  前端控制器
 * </p>
 *
 * @author ShenHuaJie
 * @since 2017-03-12
 */
@Controller
@RequestMapping("/feedback")
public class SysFeedbackController extends BaseController<SysFeedback, SysFeedbackService> {
    @ApiOperation(value = "查询反馈")
    @RequiresPermissions("cms.feedback.read")
    @GetMapping(value = "/read/page")
    public Object query(HttpServletRequest request) {
        Map<String, Object> param = WebUtil.getParameter(request);
        return super.query(param);
    }

    @ApiOperation(value = "反馈详情")
    @RequiresPermissions("cms.feedback.read")
    @GetMapping(value = "/read/detail")
    public Object get(SysFeedback param) {
        return super.get(param);
    }

    @Override
    @PostMapping
    @ApiOperation(value = "修改反馈")
    @RequiresPermissions("cms.feedback.update")
    public Object update(SysFeedback param) {
        return super.update(param);
    }

    @Override
    @DeleteMapping
    @ApiOperation(value = "删除反馈")
    @RequiresPermissions("cms.feedback.delete")
    public Object delete(SysFeedback param) {
        return super.delete(param);
    }
}