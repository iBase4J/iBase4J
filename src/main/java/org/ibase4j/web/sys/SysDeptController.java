package org.ibase4j.web.sys;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.model.sys.SysDept;
import org.ibase4j.service.sys.ISysDeptService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.ibase4j.core.base.BaseController;

/**
 * 部门管理控制类
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:13:31
 */
@RestController
@Api(value = "部门管理", description = "部门管理")
@RequestMapping(value = "dept")
public class SysDeptController extends BaseController<SysDept, ISysDeptService> {

	@ApiOperation(value = "查询部门")
	@RequiresPermissions("sys.base.dept.read")
	@PutMapping(value = "/read/list")
	public Object list(ModelMap modelMap, @RequestBody Map<String, Object> param) {
		return super.queryList(modelMap, param);
	}

	@ApiOperation(value = "查询部门")
	@RequiresPermissions("sys.base.dept.read")
	@PutMapping(value = "/read/page")
	public Object query(ModelMap modelMap, @RequestBody Map<String, Object> param) {
		return super.query(modelMap, param);
	}

	@ApiOperation(value = "部门详情")
	@RequiresPermissions("sys.base.dept.read")
	@PutMapping(value = "/read/detail")
	public Object get(ModelMap modelMap, @RequestBody SysDept param) {
		return super.get(modelMap, param);
	}

	@PostMapping
	@ApiOperation(value = "修改部门")
	@RequiresPermissions("sys.base.dept.update")
	public Object update(ModelMap modelMap, @RequestBody SysDept param) {
		return super.update(modelMap, param);
	}

	@DeleteMapping
	@ApiOperation(value = "删除部门")
	@RequiresPermissions("sys.base.dept.delete")
	public Object delete(ModelMap modelMap, @RequestBody SysDept param) {
		return super.delete(modelMap, param);
	}
}
