package org.ibase4j.web.sys;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.ibase4j.core.base.BaseController;
import org.ibase4j.core.util.Request2ModelUtil;
import org.ibase4j.model.sys.SysParam;
import org.ibase4j.service.sys.SysParamService;

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
@RequestMapping(value = "param", method = RequestMethod.POST)
public class SysParamController extends BaseController {
	@Autowired
	private SysParamService sysParamService;

	@ApiOperation(value = "查询系统参数")
	@RequestMapping(value = "/read/list")
	@RequiresPermissions("sys.param.read")
	public Object get(HttpServletRequest request, ModelMap modelMap,
			@RequestBody(required = false) Map<String, Object> sysParam) {
		Page<?> list = sysParamService.query(sysParam);
		return setSuccessModelMap(modelMap, list);
	}

	// 详细信息
	@ApiOperation(value = "系统参数详情")
	@RequiresPermissions("sys.param.read")
	@RequestMapping(value = "/read/detail")
	public Object detail(ModelMap modelMap, @RequestParam(value = "id", required = false) Long id) {
		SysParam record = sysParamService.queryById(id);
		return setSuccessModelMap(modelMap, record);
	}

	// 修改
	@ApiOperation(value = "修改系统参数")
	@RequiresPermissions("sys.param.update")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Object update(HttpServletRequest request, ModelMap modelMap) {
		SysParam record = Request2ModelUtil.covert(SysParam.class, request);
		sysParamService.update(record);
		return setSuccessModelMap(modelMap);
	}

	// 删除
	@ApiOperation(value = "删除系统参数")
	@RequiresPermissions("sys.param.delete")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Object delete(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "id", required = false) Long id) {
		sysParamService.delete(id);
		return setSuccessModelMap(modelMap);
	}
}
