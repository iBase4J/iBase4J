package org.ibase4j.web;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.service.ISysEventService;
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
 * 系统日志控制类
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:13:31
 */
@RestController
@Api(value = "系统日志", description = "系统日志")
@RequestMapping(value = "event")
public class SysEventController extends BaseController {
	@Autowired
	private ISysEventService sysEventService;

	@ApiOperation(value = "查询新闻")
	@RequiresPermissions("public.news.read")
	@RequestMapping(value = "/read/list", method = RequestMethod.PUT)
	public Object get(ModelMap modelMap, @RequestBody Map<String, Object> params) {
		Page<?> list = sysEventService.query(params);
		return setSuccessModelMap(modelMap, list);
	}
}
