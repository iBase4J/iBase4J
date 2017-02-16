package org.ibase4j.web;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.core.base.AbstractController;
import org.ibase4j.model.SysUnit;
import org.ibase4j.provider.ISysProvider;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 单位管理控制类
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:13:31
 */
@RestController
@Api(value = "单位管理", description = "单位管理")
@RequestMapping(value = "unit")
public class SysUnitController extends AbstractController<ISysProvider> {
	public String getService() {
		return "sysUnitService";
	}

	@ApiOperation(value = "查询单位")
	@RequiresPermissions("sys.base.unit.read")
	@PutMapping(value = "/read/list")
	public Object query(ModelMap modelMap, @RequestBody Map<String, Object> param) {
		return super.query(modelMap, param);
	}

	@ApiOperation(value = "单位详情")
	@RequiresPermissions("sys.base.unit.read")
	@PutMapping(value = "/read/detail")
	public Object get(ModelMap modelMap, @RequestBody SysUnit param) {
		return super.get(modelMap, param);
	}

	@PostMapping
	@ApiOperation(value = "修改单位")
	@RequiresPermissions("sys.base.unit.update")
	public Object update(ModelMap modelMap, @RequestBody SysUnit param) {
		return super.update(modelMap, param);
	}

	@DeleteMapping
	@ApiOperation(value = "删除单位")
	@RequiresPermissions("sys.base.unit.delete")
	public Object delete(ModelMap modelMap, @RequestBody SysUnit param) {
		return super.delete(modelMap, param);
	}
}
