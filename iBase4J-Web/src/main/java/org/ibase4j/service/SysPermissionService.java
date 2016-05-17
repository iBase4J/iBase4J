package org.ibase4j.service;

import org.ibase4j.facade.sys.SysPermissionFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysPermissionService {
	@Autowired
	private SysPermissionFacade sysPermissionFacade;

	public boolean doCheckPermissionByUserId(Integer userId, String url) {
		return sysPermissionFacade.doCheckPermissionByUserId(userId, url);
	}
}
