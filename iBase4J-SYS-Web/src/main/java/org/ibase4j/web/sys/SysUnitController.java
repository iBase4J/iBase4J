package org.ibase4j.web.sys;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.core.base.BaseController;
import org.ibase4j.model.sys.SysUnit;
import org.ibase4j.service.sys.SysUnitService;
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
 * 单位管理控制类
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:13:31
 */
@RestController
@Api(value = "单位管理", description = "单位管理")
@RequestMapping(value = "unit")
public class SysUnitController extends BaseController {
	@Autowired
	private SysUnitService sysUnitService;

	@ApiOperation(value = "查询单位")
	@RequiresPermissions("sys.base.unit.read")
	@RequestMapping(value = "/read/list", method = RequestMethod.PUT)
	public Object get(ModelMap modelMap, @RequestBody Map<String, Object> params) {
		Page<?> list = sysUnitService.query(params);
		return setSuccessModelMap(modelMap, list);
	}

	@ApiOperation(value = "单位详情")
	@RequiresPermissions("sys.base.unit.read")
	@RequestMapping(value = "/read/detail", method = RequestMethod.PUT)
	public Object detail(ModelMap modelMap, @RequestBody SysUnit params) {
		SysUnit record = sysUnitService.queryById(params.getId());
		return setSuccessModelMap(modelMap, record);
	}

	@ApiOperation(value = "修改单位")
	@RequiresPermissions("sys.base.unit.update")
	@RequestMapping(method = RequestMethod.POST)
	public Object update(ModelMap modelMap, @RequestBody SysUnit record) {
		sysUnitService.update(record);
		return setSuccessModelMap(modelMap);
	}

	@ApiOperation(value = "删除单位")
	@RequiresPermissions("sys.base.unit.delete")
	@RequestMapping(method = RequestMethod.DELETE)
	public Object delete(ModelMap modelMap, @RequestBody SysUnit record) {
		sysUnitService.delete(record.getId());
		return setSuccessModelMap(modelMap);
	}
}
