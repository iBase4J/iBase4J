package org.ibase4j.web.sys;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.ibase4j.core.base.BaseController;
import org.ibase4j.model.sys.SysDept;
import org.ibase4j.service.sys.SysDeptService;

import com.baomidou.mybatisplus.plugins.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 部门管理控制类
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:13:31
 */
@RestController
@Api(value = "部门管理", description = "部门管理")
@RequestMapping(value = "dept")
public class SysDeptController extends BaseController {
	@Autowired
	private SysDeptService sysDeptService;

	@ApiOperation(value = "查询部门")
	@RequiresPermissions("sys.dept.read")
	@RequestMapping(value = "/read/list", method = RequestMethod.PUT)
	public Object get(ModelMap modelMap, @RequestBody Map<String, Object> sysDept) {
		Page<?> list = sysDeptService.query(sysDept);
		return setSuccessModelMap(modelMap, list);
	}

	@ApiOperation(value = "部门详情")
	@RequiresPermissions("sys.dept.read")
	@RequestMapping(value = "/read/detail", method = RequestMethod.PUT)
	public Object detail(ModelMap modelMap, @RequestBody SysDept sysDept) {
		SysDept record = sysDeptService.queryById(sysDept.getId());
		return setSuccessModelMap(modelMap, record);
	}

	@ApiOperation(value = "修改部门")
	@RequiresPermissions("sys.dept.update")
	@RequestMapping(method = RequestMethod.POST)
	public Object update(ModelMap modelMap, @RequestBody SysDept record) {
		sysDeptService.update(record);
		return setSuccessModelMap(modelMap);
	}

	@ApiOperation(value = "删除部门")
	@RequiresPermissions("sys.dept.delete")
	@RequestMapping(method = RequestMethod.DELETE)
	public Object delete(ModelMap modelMap, @RequestBody SysDept record) {
		sysDeptService.delete(record.getId());
		return setSuccessModelMap(modelMap);
	}
}
