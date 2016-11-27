package org.ibase4j.web.sys;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.core.base.BaseController;
import org.ibase4j.core.util.Request2ModelUtil;
import org.ibase4j.core.util.WebUtil;
import org.ibase4j.model.sys.SysDept;
import org.ibase4j.service.sys.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping(value = "dept", method = RequestMethod.POST)
public class SysDeptController extends BaseController {
	@Autowired
	private SysDeptService sysDeptService;

	// 查询部门
	@ApiOperation(value = "查询部门")
	@RequiresPermissions("sys.dept.read")
	@RequestMapping(value = "/read/list")
	public Object get(HttpServletRequest request, ModelMap modelMap) {
		Map<String, Object> params = WebUtil.getParameterMap(request);
		Page<?> list = sysDeptService.query(params);
		return setSuccessModelMap(modelMap, list);
	}

	// 详细信息
	@ApiOperation(value = "部门详情")
	@RequiresPermissions("sys.dept.read")
	@RequestMapping(value = "/read/detail")
	public Object detail(ModelMap modelMap, @RequestParam(value = "id", required = false) Long id) {
		SysDept record = sysDeptService.queryById(id);
		return setSuccessModelMap(modelMap, record);
	}

	// 新增部门
	@ApiOperation(value = "添加部门")
	@RequiresPermissions("sys.dept.add")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object add(HttpServletRequest request, ModelMap modelMap) {
		SysDept record = Request2ModelUtil.covert(SysDept.class, request);
		sysDeptService.add(record);
		return setSuccessModelMap(modelMap);
	}

	// 修改部门
	@ApiOperation(value = "修改部门")
	@RequiresPermissions("sys.dept.update")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Object update(HttpServletRequest request, ModelMap modelMap) {
		SysDept record = Request2ModelUtil.covert(SysDept.class, request);
		sysDeptService.update(record);
		return setSuccessModelMap(modelMap);
	}

	// 删除部门
	@ApiOperation(value = "删除部门")
	@RequiresPermissions("sys.dept.delete")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Object delete(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "id", required = false) Long id) {
		sysDeptService.delete(id);
		return setSuccessModelMap(modelMap);
	}
}
