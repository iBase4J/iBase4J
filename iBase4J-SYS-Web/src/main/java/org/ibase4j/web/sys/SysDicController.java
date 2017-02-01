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
import org.ibase4j.model.sys.SysDic;
import org.ibase4j.model.sys.SysDicIndex;
import org.ibase4j.service.sys.SysDicService;

import com.baomidou.mybatisplus.plugins.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 字典管理
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:14:34
 */
@RestController
@Api(value = "字典管理", description = "字典管理")
@RequestMapping(method = RequestMethod.POST)
public class SysDicController extends BaseController {
	@Autowired
	private SysDicService sysDicService;

	@ApiOperation(value = "查询字典")
	@RequiresPermissions("sys.dic.read")
	@RequestMapping(value = "dicIndex/read/list")
	public Object getDicIndex(HttpServletRequest request, ModelMap modelMap,
			@RequestBody(required = false) Map<String, Object> sysDicIndex) {
		Page<?> list = sysDicService.queryDicIndex(sysDicIndex);
		return setSuccessModelMap(modelMap, list);
	}

	@ApiOperation(value = "字典详情")
	@RequiresPermissions("sys.dic.read")
	@RequestMapping(value = "dicIndex/read/detail")
	public Object detail(ModelMap modelMap, @RequestParam(value = "id", required = false) Long id) {
		SysDicIndex record = sysDicService.queryDicIndexById(id);
		return setSuccessModelMap(modelMap, record);
	}

	@ApiOperation(value = "根据关键字查询字典列表")
	@RequiresPermissions("sys.dic.read")
	@RequestMapping(value = "dic/read/key")
	public Object getDicByKey(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "key", required = false) String key) {
		Map<String, String> result = sysDicService.queryDicByDicIndexKey(key);
		return setSuccessModelMap(modelMap, result);
	}

	@ApiOperation(value = "添加字典")
	@RequiresPermissions("sys.dic.add")
	@RequestMapping(value = "dicIndex/add", method = RequestMethod.POST)
	public Object addDicIndex(HttpServletRequest request, ModelMap modelMap) {
		SysDicIndex record = Request2ModelUtil.covert(SysDicIndex.class, request);
		sysDicService.addDicIndex(record);
		return setSuccessModelMap(modelMap);
	}

	@ApiOperation(value = "修改字典")
	@RequiresPermissions("sys.dic.update")
	@RequestMapping(value = "dicIndex/update", method = RequestMethod.POST)
	public Object updateDicIndex(HttpServletRequest request, ModelMap modelMap) {
		SysDicIndex record = Request2ModelUtil.covert(SysDicIndex.class, request);
		sysDicService.updateDicIndex(record);
		return setSuccessModelMap(modelMap);
	}

	@ApiOperation(value = "删除字典")
	@RequiresPermissions("sys.dic.delete")
	@RequestMapping(value = "dicIndex/delete", method = RequestMethod.POST)
	public Object deleteDicIndex(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "id", required = false) Long id) {
		sysDicService.deleteDicIndex(id);
		return setSuccessModelMap(modelMap);
	}

	@ApiOperation(value = "查询字典项")
	@RequiresPermissions("sys.dic.read")
	@RequestMapping(value = "dic/read/list")
	public Object getDic(HttpServletRequest request, ModelMap modelMap,
			@RequestBody(required = false) Map<String, Object> sysDic) {
		Page<?> list = sysDicService.queryDic(sysDic);
		return setSuccessModelMap(modelMap, list);
	}

	@ApiOperation(value = "字典项详情")
	@RequiresPermissions("sys.dic.read")
	@RequestMapping(value = "dic/read/detail")
	public Object dicDetail(ModelMap modelMap, @RequestParam(value = "id", required = false) Long id) {
		SysDic record = sysDicService.queryDicById(id);
		return setSuccessModelMap(modelMap, record);
	}

	@ApiOperation(value = "添加字典项")
	@RequiresPermissions("sys.dic.add")
	@RequestMapping(value = "dic/add", method = RequestMethod.POST)
	public Object addDic(HttpServletRequest request, ModelMap modelMap) {
		SysDic record = Request2ModelUtil.covert(SysDic.class, request);
		sysDicService.addDic(record);
		return setSuccessModelMap(modelMap);
	}

	@ApiOperation(value = "修改字典项")
	@RequiresPermissions("sys.dic.update")
	@RequestMapping(value = "dic/update", method = RequestMethod.POST)
	public Object updateDic(HttpServletRequest request, ModelMap modelMap) {
		SysDic record = Request2ModelUtil.covert(SysDic.class, request);
		sysDicService.updateDic(record);
		return setSuccessModelMap(modelMap);
	}

	@ApiOperation(value = "删除字典项")
	@RequiresPermissions("sys.dic.delete")
	@RequestMapping(value = "dic/delete", method = RequestMethod.POST)
	public Object deleteDic(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "id", required = false) Long id) {
		sysDicService.deleteDic(id);
		return setSuccessModelMap(modelMap);
	}
}
