package org.ibase4j.web;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.core.base.BaseController;
import org.ibase4j.model.SysEmailTemplate;
import org.ibase4j.service.SysEmailTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 邮件模版管理控制类
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:13:31
 */
@RestController
@Api(value = "邮件模版管理", description = "邮件模版管理")
@RequestMapping(value = "emailTemplate")
public class SysEmailTemplateController extends BaseController {
	@Autowired
	private SysEmailTemplateService sysEmailTemplateService;

	@ApiOperation(value = "查询邮件模版")
	@RequiresPermissions("sys.email.template.read")
	@RequestMapping(value = "/read/list", method = RequestMethod.PUT)
	public Object get(ModelMap modelMap, @RequestBody Map<String, Object> params) {
		Page<?> list = sysEmailTemplateService.query(params);
		return setSuccessModelMap(modelMap, list);
	}

	@ApiOperation(value = "邮件模版详情")
	@RequiresPermissions("sys.email.template.read")
	@RequestMapping(value = "/read/detail", method = RequestMethod.PUT)
	public Object detail(ModelMap modelMap, @RequestBody SysEmailTemplate params) {
		SysEmailTemplate record = sysEmailTemplateService.queryById(params.getId());
		return setSuccessModelMap(modelMap, record);
	}

	@ApiOperation(value = "修改邮件模版")
	@RequiresPermissions("sys.email.template.update")
	@RequestMapping(method = RequestMethod.POST)
	public Object update(ModelMap modelMap, @RequestBody SysEmailTemplate record) {
		sysEmailTemplateService.update(record);
		return setSuccessModelMap(modelMap);
	}

	@ApiOperation(value = "删除邮件模版")
	@RequiresPermissions("sys.email.template.delete")
	@RequestMapping(method = RequestMethod.DELETE)
	public Object delete(ModelMap modelMap, @RequestBody SysEmailTemplate record) {
		sysEmailTemplateService.delete(record.getId());
		return setSuccessModelMap(modelMap);
	}
}
