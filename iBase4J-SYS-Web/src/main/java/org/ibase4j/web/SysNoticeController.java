package org.ibase4j.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.model.SysNotice;
import org.ibase4j.service.SysNoticeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.ibase4j.core.base.BaseController;
import top.ibase4j.core.util.WebUtil;

/**
 * 通知管理控制类
 *
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:13:31
 */
@RestController
@Api(value = "通知管理", description = "通知管理")
@RequestMapping(value = "notice")
public class SysNoticeController extends BaseController<SysNotice, SysNoticeService> {
    @ApiOperation(value = "查询通知")
    @RequiresPermissions("cms.notice.read")
    @GetMapping("/read/page")
    public Object query(HttpServletRequest request) {
        Map<String, Object> param = WebUtil.getParameter(request);
        return super.query(param);
    }

    @ApiOperation(value = "通知详情")
    @RequiresPermissions("cms.notice.read")
    @GetMapping("/read/detail")
    public Object get(SysNotice param) {
        return super.get(param);
    }

    @Override
    @ApiOperation(value = "修改通知")
    @RequiresPermissions("cms.notice.update")
    @RequestMapping(method = RequestMethod.POST)
    public Object update(SysNotice param) {
        if (param.getStatus() == null) {
            param.setStatus("0");
        }
        return super.update(param);
    }

    @Override
    @ApiOperation(value = "删除通知")
    @RequiresPermissions("cms.notice.delete")
    @RequestMapping(method = RequestMethod.DELETE)
    public Object delete(SysNotice param) {
        return super.delete(param);
    }
}
