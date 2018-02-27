package org.ibase4j.web;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.model.SysMsg;
import org.ibase4j.service.ISysMsgService;
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
 * 短信 前端控制器
 * </p>
 *
 * @author ShenHuaJie
 * @since 2017-03-12
 */
@Controller
@RequestMapping("/msg")
public class SysMsgController extends BaseController<SysMsg, ISysMsgService> {
	@ApiOperation(value = "查询短信")
	@RequiresPermissions("msg.list.read")
	@PutMapping(value = "/read/list")
	public Object query(ModelMap modelMap, @RequestBody Map<String, Object> param) {
		return super.query(modelMap, param);
	}

	@ApiOperation(value = "短信详情")
	@RequiresPermissions("msg.list.read")
	@PutMapping(value = "/read/detail")
	public Object get(ModelMap modelMap, @RequestBody SysMsg param) {
		return super.get(modelMap, param);
	}

	@PostMapping
	@ApiOperation(value = "修改短信")
	@RequiresPermissions("msg.list.update")
	public Object update(ModelMap modelMap, @RequestBody SysMsg param) {
		return super.update(modelMap, param);
	}

	@DeleteMapping
	@ApiOperation(value = "删除短信")
	@RequiresPermissions("msg.list.delete")
	public Object delete(ModelMap modelMap, @RequestBody SysMsg param) {
		return super.delete(modelMap, param);
	}
}