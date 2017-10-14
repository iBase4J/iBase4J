package org.ibase4j.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.ibase4j.core.util.WebUtil;
import org.ibase4j.model.SysDic;
import org.ibase4j.provider.ISysProvider;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 字典管理
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:14:34
 */
@Controller
@Api(value = "字典管理", description = "APP-字典接口")
@RequestMapping(value = "/app/dic/")
public class SysDicController extends AppBaseController<ISysProvider> {

    public String getService() {
        return "sysDicService";
    }

    @ApiOperation(value = "查询字典项", produces = MediaType.APPLICATION_JSON_VALUE, response = SysDic.class)
    @RequestMapping(value = "query.api", method = {RequestMethod.GET, RequestMethod.POST})
    public Object queryList(HttpServletRequest request, String type) {
        Map<String, Object> param = WebUtil.getParameter(request);
        Assert.notNull(param.get("type"), "类型type不能为空.");
        param.put("orderBy", "type_,sort_no");
        ModelMap modelMap = new ModelMap();
        modelMap.put("listKey", "dicLlist");
        return super.queryList(modelMap, param);
    }
}
