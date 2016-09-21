package org.ibase4j.service.sys;

import java.util.List;
import java.util.Map;

import org.ibase4j.core.base.BaseService;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboReference;
import org.ibase4j.model.sys.SysRole;
import org.ibase4j.provider.sys.ISysRoleProvider;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;

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

    public Page<?> queryBean(Map<String, Object> params) {
        return provider.queryBean(params);
    }

    public List<String> getPermissions(String id) {
        return provider.getPermissions(id);
    }
}
