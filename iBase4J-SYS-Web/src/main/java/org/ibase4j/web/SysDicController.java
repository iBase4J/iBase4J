package org.ibase4j.web;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.core.base.BaseController;
import org.ibase4j.model.SysDic;
import org.ibase4j.model.SysDicIndex;
import org.ibase4j.service.SysDicService;
import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private SysDicService sysDicService;

	@ApiOperation(value = "查询字典")
	@RequiresPermissions("sys.base.dic.read")
	@PutMapping(value = "dicIndex/read/list")
	public Object getDicIndex(ModelMap modelMap, @RequestBody(required = false) Map<String, Object> sysDicIndex) {
		Page<?> list = sysDicService.queryDicIndex(sysDicIndex);
		return setSuccessModelMap(modelMap, list);
	}

	@ApiOperation(value = "字典详情")
	@RequiresPermissions("sys.base.dic.read")
	@PutMapping(value = "dicIndex/read/detail")
	public Object detail(ModelMap modelMap, @RequestBody(required = false) SysDicIndex param) {
		SysDicIndex record = sysDicService.queryDicIndexById(param.getId());
		return setSuccessModelMap(modelMap, record);
	}

	@ApiOperation(value = "根据关键字查询字典列表")
	@PutMapping(value = "dic/read/key")
	public Object getDicByKey(ModelMap modelMap, @RequestBody SysDicIndex record) {
		Map<String, String> result = sysDicService.queryDicByDicIndexKey(record.getKey());
		return setSuccessModelMap(modelMap, result);
	}

	@ApiOperation(value = "修改字典")
	@PostMapping(value = "dicIndex")
	@RequiresPermissions("sys.base.dic.update")
	public Object updateDicIndex(ModelMap modelMap, @RequestBody SysDicIndex record) {
		sysDicService.updateDicIndex(record);
		return setSuccessModelMap(modelMap);
	}

	@ApiOperation(value = "删除字典")
	@DeleteMapping(value = "dicIndex")
	@RequiresPermissions("sys.base.dic.delete")
	public Object deleteDicIndex(ModelMap modelMap, @RequestBody SysDicIndex record) {
		sysDicService.deleteDicIndex(record.getId());
		return setSuccessModelMap(modelMap);
	}

	@ApiOperation(value = "查询字典项")
	@RequiresPermissions("sys.base.dic.read")
	@PutMapping(value = "dic/read/list")
	public Object getDic(ModelMap modelMap, @RequestBody(required = false) Map<String, Object> sysDic) {
		sysDic.put("orderBy", "sort_no");
		Page<?> list = sysDicService.queryDic(sysDic);
		return setSuccessModelMap(modelMap, list);
	}

	@ApiOperation(value = "字典项详情")
	@RequiresPermissions("sys.base.dic.read")
	@PutMapping(value = "dic/read/detail")
	public Object dicDetail(ModelMap modelMap, @RequestBody(required = false) SysDic sysDic) {
		SysDic record = sysDicService.queryDicById(sysDic.getId());
		return setSuccessModelMap(modelMap, record);
	}

	@PostMapping(value = "dic")
	@ApiOperation(value = "修改字典项")
	@RequiresPermissions("sys.base.dic.update")
	public Object updateDic(ModelMap modelMap, @RequestBody(required = false) SysDic record) {
		sysDicService.updateDic(record);
		return setSuccessModelMap(modelMap);
	}

	@DeleteMapping(value = "dic")
	@ApiOperation(value = "删除字典项")
	@RequiresPermissions("sys.base.dic.delete")
	public Object deleteDic(ModelMap modelMap, @RequestBody(required = false) SysDic record) {
		sysDicService.deleteDic(record.getId());
		return setSuccessModelMap(modelMap);
	}
}
