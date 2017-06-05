package org.ibase4j.web;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.core.base.BaseController;
import org.ibase4j.model.SysParam;
import org.ibase4j.service.SysParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 参数管理
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:15:19
 */
@RestController
@Api(value = "系统参数管理", description = "系统参数管理")
@RequestMapping(value = "param")
public class SysParamController extends BaseController {
	@Autowired
	private SysParamService sysParamService;

	@PutMapping(value = "/read/list")
	@ApiOperation(value = "查询系统参数")
	@RequiresPermissions("sys.base.param.read")
	public Object get(ModelMap modelMap, @RequestBody(required = false) Map<String, Object> sysParam) {
		Page<?> list = sysParamService.query(sysParam);
		return setSuccessModelMap(modelMap, list);
	}

	@PutMapping(value = "/read/detail")
	@ApiOperation(value = "系统参数详情")
	@RequiresPermissions("sys.base.param.read")
	public Object detail(ModelMap modelMap, @RequestBody SysParam param) {
		SysParam record = sysParamService.queryById(param.getId());
		return setSuccessModelMap(modelMap, record);
	}

	@PostMapping
	@ApiOperation(value = "修改系统参数")
	@RequiresPermissions("sys.base.param.update")
	public Object update(@RequestBody SysParam record, ModelMap modelMap) {
		sysParamService.update(record);
		return setSuccessModelMap(modelMap);
	}

	@DeleteMapping
	@ApiOperation(value = "删除系统参数")
	@RequiresPermissions("sys.base.param.delete")
	public Object delete(@RequestBody SysParam param, ModelMap modelMap) {
		sysParamService.delete(param.getId());
		return setSuccessModelMap(modelMap);
	}
}
