package org.ibase4j.service;

import org.ibase4j.core.base.BaseService;
import org.ibase4j.model.SysRole;
import org.ibase4j.provider.ISysRoleProvider;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * @author ShenHuaJie
 * @version 2016年5月31日 上午11:09:29
 */
@Service
public class SysRoleService extends BaseService<ISysRoleProvider, SysRole> {
	@Reference
    public void setProvider(ISysRoleProvider provider) {
        this.provider = provider;
    }
}
