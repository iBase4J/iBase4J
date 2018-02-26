package org.ibase4j.web;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.model.SysNews;
import org.ibase4j.service.ISysNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.ibase4j.core.base.BaseController;

/**
 * 新闻管理控制类
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:13:31
 */
@RestController
@Api(value = "新闻管理", description = "新闻管理")
@RequestMapping(value = "news")
public class SysNewsController extends BaseController {
	@Autowired
	private ISysNewsService sysNewsService;

	@ApiOperation(value = "查询新闻")
	@RequiresPermissions("public.news.read")
	@RequestMapping(value = "/read/list", method = RequestMethod.PUT)
	public Object get(ModelMap modelMap, @RequestBody Map<String, Object> params) {
		Page<?> list = sysNewsService.query(params);
		return setSuccessModelMap(modelMap, list);
	}

	@ApiOperation(value = "新闻详情")
	@RequiresPermissions("public.news.read")
	@RequestMapping(value = "/read/detail", method = RequestMethod.PUT)
	public Object detail(ModelMap modelMap, @RequestBody SysNews params) {
		SysNews record = sysNewsService.queryById(params.getId());
		return setSuccessModelMap(modelMap, record);
	}

	@ApiOperation(value = "修改新闻")
	@RequiresPermissions("public.news.update")
	@RequestMapping(method = RequestMethod.POST)
	public Object update(ModelMap modelMap, @RequestBody SysNews record) {
		sysNewsService.update(record);
		return setSuccessModelMap(modelMap);
	}

	@ApiOperation(value = "删除新闻")
	@RequiresPermissions("public.news.delete")
	@RequestMapping(method = RequestMethod.DELETE)
	public Object delete(ModelMap modelMap, @RequestBody SysNews record) {
		sysNewsService.delete(record.getId());
		return setSuccessModelMap(modelMap);
	}
}
