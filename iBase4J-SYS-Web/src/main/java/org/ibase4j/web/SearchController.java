package org.ibase4j.web;

import java.util.List;
import java.util.Map;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.ibase4j.core.base.AbstractController;
import org.ibase4j.core.base.Parameter;
import org.ibase4j.provider.ISysProvider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "搜索", description = "搜索")
public class SearchController extends AbstractController<ISysProvider> {

	public String getService() {
		return "searchService";
	}

	@PutMapping("query")
	@ApiOperation(value = "全库搜索")
	public Object query(ModelMap modelMap, @RequestBody Map<String, Object> param) {
		Parameter parameter = new Parameter(getService(), "query", param);
		List<?> list = provider.execute(parameter).getResultList();
		return setSuccessModelMap(modelMap, list);
	}
}
