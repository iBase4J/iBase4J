package org.ibase4j.provider;

import java.util.Map;

import org.ibase4j.core.base.BaseProvider;
import org.ibase4j.model.generator.SysRole;
import org.ibase4j.model.sys.SysRoleBean;

import com.github.pagehelper.PageInfo;

public interface SysRoleProvider extends BaseProvider<SysRole> {
	public PageInfo<SysRoleBean> queryBean(Map<String, Object> params);
}
