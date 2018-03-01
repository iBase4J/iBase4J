package org.ibase4j.web.sys;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.model.sys.SysMsgConfig;
import org.ibase4j.service.sys.SysMsgConfigService;
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
 * 前端控制器
 * </p>
 *
 * @author ShenHuaJie
 * @since 2017-03-12
 */
@Controller
@RequestMapping("/msgConfig")
public class SysMsgConfigController extends BaseController<SysMsgConfig, SysMsgConfigService> {
	@ApiOperation(value = "查询")
	@RequiresPermissions("msg.config.read")
	@PutMapping(value = "/read/list")
	public Object query(ModelMap modelMap, @RequestBody Map<String, Object> param) {
		return super.query(modelMap, param);
	}

	@ApiOperation(value = "详情")
	@RequiresPermissions("msg.config.read")
	@PutMapping(value = "/read/detail")
	public Object get(ModelMap modelMap, @RequestBody SysMsgConfig param) {
		return super.get(modelMap, param);
	}

	@PostMapping
	@ApiOperation(value = "修改")
	@RequiresPermissions("msg.config.update")
	public Object update(ModelMap modelMap, @RequestBody SysMsgConfig param) {
		return super.update(modelMap, param);
	}

	@DeleteMapping
	@ApiOperation(value = "删除")
	@RequiresPermissions("msg.config.delete")
	public Object delete(ModelMap modelMap, @RequestBody SysMsgConfig param) {
		return super.delete(modelMap, param);
	}
}