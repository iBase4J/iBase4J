package org.ibase4j.provider.sys;

import org.ibase4j.core.base.BaseProvider;
import org.ibase4j.model.generator.SysPermission;

/**
 * URL权限管理
 * 
 * @author ShenHuaJie
 */
public interface SysPermissionProvider extends BaseProvider<SysPermission> {

	public boolean doCheckPermissionByUserId(Integer userId, String url);
}
