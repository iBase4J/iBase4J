package org.ibase4j.facade.sys;

/**
 * URL权限管理
 * 
 * @author ShenHuaJie
 */
public interface SysPermissionFacade {

	public boolean doCheckPermissionByUserId(Integer userId, String url);
}
