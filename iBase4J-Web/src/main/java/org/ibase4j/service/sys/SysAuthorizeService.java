package org.ibase4j.service.sys;

import java.util.List;

import org.ibase4j.model.sys.SysMenuBean;
import org.ibase4j.provider.sys.SysAuthorizeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:16:00
 */
@Service
public class SysAuthorizeService {
	@Autowired
	private SysAuthorizeProvider sysAuthorizeProvider;

	public List<SysMenuBean> queryAuthorizeByUserId(Integer id) {
		return sysAuthorizeProvider.queryAuthorizeByUserId(id);
	}

	public List<String> queryPermissionByUserId(Integer userId) {
		return sysAuthorizeProvider.queryPermissionByUserId(userId);
	}

}
