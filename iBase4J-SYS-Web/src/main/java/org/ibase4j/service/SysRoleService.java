package org.ibase4j.service;

import org.springframework.stereotype.Service;
import org.ibase4j.core.base.BaseService;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboReference;
import org.ibase4j.model.SysRole;
import org.ibase4j.provider.ISysRoleProvider;

/**
 * @author ShenHuaJie
 * @version 2016年5月31日 上午11:09:29
 */
@Service
public class SysRoleService extends BaseService<ISysRoleProvider, SysRole> {
    @DubboReference
    public void setProvider(ISysRoleProvider provider) {
        this.provider = provider;
    }
}
