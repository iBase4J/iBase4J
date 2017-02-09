package org.ibase4j.web.sys;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.core.base.BaseController;
import org.ibase4j.model.sys.SysEmail;
import org.ibase4j.service.sys.SysEmailService;
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
 * 邮件管理控制类
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:13:31
 */
@RestController
@Api(value = "邮件管理", description = "邮件管理")
@RequestMapping(value = "email")
public class SysEmailController extends BaseController {
	@Autowired
	private SysEmailService sysEmailService;

	@ApiOperation(value = "查询邮件")
	@RequiresPermissions("sys.email.list.read")
	@RequestMapping(value = "/read/list", method = RequestMethod.PUT)
	public Object get(ModelMap modelMap, @RequestBody Map<String, Object> params) {
		Page<?> list = sysEmailService.query(params);
		return setSuccessModelMap(modelMap, list);
	}

	@ApiOperation(value = "邮件详情")
	@RequiresPermissions("sys.email.list.read")
	@RequestMapping(value = "/read/detail", method = RequestMethod.PUT)
	public Object detail(ModelMap modelMap, @RequestBody SysEmail params) {
		SysEmail record = sysEmailService.queryById(params.getId());
		return setSuccessModelMap(modelMap, record);
	}

	@ApiOperation(value = "修改邮件")
	@RequiresPermissions("sys.email.list.update")
	@RequestMapping(method = RequestMethod.POST)
	public Object update(ModelMap modelMap, @RequestBody SysEmail record) {
		sysEmailService.update(record);
		return setSuccessModelMap(modelMap);
	}

	@ApiOperation(value = "删除邮件")
	@RequiresPermissions("sys.email.list.delete")
	@RequestMapping(method = RequestMethod.DELETE)
	public Object delete(ModelMap modelMap, @RequestBody SysEmail record) {
		sysEmailService.delete(record.getId());
		return setSuccessModelMap(modelMap);
	}
}
