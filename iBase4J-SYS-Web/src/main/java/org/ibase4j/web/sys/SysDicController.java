package org.ibase4j.web.sys;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.core.base.BaseController;
import org.ibase4j.core.base.BaseModel;
import org.ibase4j.core.base.Parameter;
import org.ibase4j.core.support.Assert;
import org.ibase4j.model.sys.SysDic;
import org.ibase4j.model.sys.SysDicIndex;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
public class SysDicController extends BaseController {

	public String getService() {
		return "sysDicService";
	}

	@ApiOperation(value = "查询字典")
	@RequiresPermissions("sys.base.dic.read")
	@PutMapping(value = "dicIndex/read/list")
	public Object getDicIndex(ModelMap modelMap, @RequestBody(required = false) Map<String, Object> param) {
		Parameter parameter = new Parameter(getService(), "queryDicIndex").setMap(param);
		Page<?> list = provider.exec(parameter).getPage();
		return setSuccessModelMap(modelMap, list);
	}

	@ApiOperation(value = "字典详情")
	@RequiresPermissions("sys.base.dic.read")
	@PutMapping(value = "dicIndex/read/detail")
	public Object detail(ModelMap modelMap, @RequestBody(required = false) SysDicIndex param) {
		Assert.notNull(param.getId(), "ID");
		Parameter parameter = new Parameter(getService(), "queryDicIndexById").setId(param.getId());
		BaseModel result = provider.exec(parameter).getModel();
		return setSuccessModelMap(modelMap, result);
	}

	@ApiOperation(value = "根据关键字查询字典列表")
	@PutMapping(value = "dic/read/key")
	public Object getDicByKey(ModelMap modelMap, @RequestBody SysDicIndex param) {
		Parameter parameter = new Parameter(getService(), "queryDicByDicIndexKey").setModel(param);
		Map<?, ?> result = provider.exec(parameter).getMap();
		return setSuccessModelMap(modelMap, result);
	}

	@ApiOperation(value = "修改字典")
	@PostMapping(value = "dicIndex")
	@RequiresPermissions("sys.base.dic.update")
	public Object updateDicIndex(ModelMap modelMap, @RequestBody SysDicIndex param) {
		Parameter parameter = new Parameter(getService(), "updateDicIndex").setModel(param);
		provider.exec(parameter);
		return setSuccessModelMap(modelMap);
	}

	@ApiOperation(value = "删除字典")
	@DeleteMapping(value = "dicIndex")
	@RequiresPermissions("sys.base.dic.delete")
	public Object deleteDicIndex(ModelMap modelMap, @RequestBody SysDicIndex param) {
		Assert.notNull(param.getId(), "ID");
		Parameter parameter = new Parameter(getService(), "deleteDicIndex").setModel(param);
		provider.exec(parameter);
		return setSuccessModelMap(modelMap);
	}

	@ApiOperation(value = "查询字典项")
	@RequiresPermissions("sys.base.dic.read")
	@PutMapping(value = "dic/read/list")
	public Object getDic(ModelMap modelMap, @RequestBody(required = false) Map<String, Object> param) {
		param.put("orderBy", "sort_no");
		Parameter parameter = new Parameter(getService(), "queryDic").setMap(param);
		Page<?> list = provider.exec(parameter).getPage();
		return setSuccessModelMap(modelMap, list);
	}

	@ApiOperation(value = "字典项详情")
	@RequiresPermissions("sys.base.dic.read")
	@PutMapping(value = "dic/read/detail")
	public Object dicDetail(ModelMap modelMap, @RequestBody(required = false) SysDic param) {
		Assert.notNull(param.getId(), "ID");
		Parameter parameter = new Parameter(getService(), "queryDicById").setId(param.getId());
		BaseModel record = provider.exec(parameter).getModel();
		return setSuccessModelMap(modelMap, record);
	}

	@PostMapping(value = "dic")
	@ApiOperation(value = "修改字典项")
	@RequiresPermissions("sys.base.dic.update")
	public Object updateDic(ModelMap modelMap, @RequestBody(required = false) SysDic param) {
		Parameter parameter = new Parameter(getService(), "updateDic").setModel(param);
		provider.exec(parameter);
		return setSuccessModelMap(modelMap);
	}

	@DeleteMapping(value = "dic")
	@ApiOperation(value = "删除字典项")
	@RequiresPermissions("sys.base.dic.delete")
	public Object deleteDic(ModelMap modelMap, @RequestBody(required = false) SysDic param) {
		Assert.notNull(param.getId(), "ID");
		Parameter parameter = new Parameter(getService(), "deleteDic").setId(param.getId());
		provider.exec(parameter);
		return setSuccessModelMap(modelMap);
	}
}
