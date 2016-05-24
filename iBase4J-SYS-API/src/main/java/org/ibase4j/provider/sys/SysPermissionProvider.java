package org.ibase4j.provider.sys;

/**
 * URL权限管理
 * 
 * @author ShenHuaJie
 */
public interface SysPermissionProvider {

	public boolean doCheckPermissionByUserId(Integer userId, String url);
}
