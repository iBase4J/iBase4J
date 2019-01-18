package org.ibase4j.web;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.model.SysUnit;
import org.ibase4j.service.SysUnitService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.ibase4j.core.base.BaseController;

/**
 * 单位管理控制类
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:13:31
 */
@RestController
@Api(value = "单位管理", description = "单位管理")
@RequestMapping(value = "unit")
public class SysUnitController extends BaseController<SysUnit, SysUnitService> {
	@ApiOperation(value = "查询单位")
	@RequiresPermissions("sys.base.unit.read")
	@GetMapping(value = "/read/list")
	public Object list(ModelMap modelMap,  Map<String, Object> param) {
		return super.queryList(modelMap, param);
	}

	@ApiOperation(value = "查询单位")
	@RequiresPermissions("sys.base.unit.read")
	@GetMapping(value = "/read/page")
	public Object query(ModelMap modelMap,  Map<String, Object> param) {
		return super.query(modelMap, param);
	}

	@ApiOperation(value = "单位详情")
	@RequiresPermissions("sys.base.unit.read")
	@GetMapping(value = "/read/detail")
	public Object get(ModelMap modelMap,  SysUnit param) {
		return super.get(modelMap, param);
	}

	@PostMapping
	@ApiOperation(value = "修改单位")
	@RequiresPermissions("sys.base.unit.update")
	public Object update(ModelMap modelMap,  SysUnit param) {
		return super.update(modelMap, param);
	}

	@DeleteMapping
	@ApiOperation(value = "删除单位")
	@RequiresPermissions("sys.base.unit.delete")
	public Object delete(ModelMap modelMap,  SysUnit param) {
		return super.delete(modelMap, param);
	}
}
