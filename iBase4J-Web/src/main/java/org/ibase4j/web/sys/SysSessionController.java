package org.ibase4j.web.sys;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.ibase4j.core.listener.SessionListener;
import org.ibase4j.core.support.BaseController;
import org.ibase4j.core.util.WebUtil;
import org.ibase4j.service.sys.SysSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

/**
 * 用户会话管理
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:13:06
 */
@RestController
@RequestMapping("/session")
public class SysSessionController extends BaseController {
	@Autowired
	private SysSessionService sysSessionService;

	// 查询会话
	@RequestMapping(value = "/read/list")
	public Object get(HttpServletRequest request, ModelMap modelMap) {
		Map<String, Object> params = WebUtil.getParameterMap(request);
		PageInfo<?> list = sysSessionService.query(params);
		Long number = SessionListener.getAllUserNumber();
		modelMap.put("userNumber", number); // 用户数大于会话数,有用户没有登录
		return setSuccessModelMap(modelMap, list);
	}

	// 删除会话
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Object update(ModelMap modelMap, @RequestParam(value = "id", required = false) Integer id) {
		sysSessionService.delete(id);
		return setSuccessModelMap(modelMap);
	}
}
