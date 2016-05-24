package org.ibase4j.service.sys;

import org.ibase4j.provider.sys.SysPermissionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:16:13
 */
@Service
public class SysPermissionService {
	@Autowired
	private SysPermissionProvider sysPermissionFacade;

	public boolean doCheckPermissionByUserId(Integer userId, String url) {
		return sysPermissionFacade.doCheckPermissionByUserId(userId, url);
	}
}
