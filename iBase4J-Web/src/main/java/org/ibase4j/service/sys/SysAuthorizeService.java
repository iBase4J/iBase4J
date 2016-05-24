package org.ibase4j.service.sys;

import java.util.List;

import org.ibase4j.mybatis.sys.model.SysMenuBean;
import org.ibase4j.provider.sys.SysAuthorizeProvider;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:16:00
 */
@Service
public class SysAuthorizeService {
	@Reference
	private SysAuthorizeProvider authorizeFacade;

	public List<SysMenuBean> queryAuthorizeByUserId(Integer id) {
		return authorizeFacade.queryAuthorizeByUserId(id);
	}

}
