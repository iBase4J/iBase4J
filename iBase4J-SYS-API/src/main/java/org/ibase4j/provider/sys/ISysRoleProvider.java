package org.ibase4j.provider.sys;

import java.util.Map;

import org.ibase4j.core.base.BaseProvider;
import org.ibase4j.model.sys.SysRole;
import org.ibase4j.model.sys.ext.SysRoleBean;

import com.baomidou.mybatisplus.plugins.Page;

public interface ISysRoleProvider extends BaseProvider<SysRole> {
    public Page<SysRoleBean> queryBean(Map<String, Object> params);
}
