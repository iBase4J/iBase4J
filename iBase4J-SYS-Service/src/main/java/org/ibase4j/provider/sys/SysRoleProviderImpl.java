package org.ibase4j.provider.sys;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.dao.sys.SysRoleMenuMapper;
import org.ibase4j.model.sys.SysRole;
import org.ibase4j.model.sys.ext.SysRoleBean;
import org.ibase4j.provider.sys.ISysRoleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * @author ShenHuaJie
 * @version 2016年5月31日 上午11:01:33
 */
@CacheConfig(cacheNames = "sysRole")
@DubboService(interfaceClass = ISysRoleProvider.class)
public class SysRoleProviderImpl extends BaseProviderImpl<SysRole> implements ISysRoleProvider {
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    public Page<SysRole> query(Map<String, Object> params) {
        Page<Long> page = this.getPage(params);
        page.setRecords(mapper.selectIdByMap(page, params));
        return getPage(page);
    }

    public Page<SysRoleBean> queryBean(Map<String, Object> params) {
        Page<Long> idPage = this.getPage(params);
        idPage.setRecords(mapper.selectIdByMap(idPage, params));
        Page<SysRoleBean> pageInfo = getPage(idPage, SysRoleBean.class);
        // 权限信息
        for (SysRoleBean bean : pageInfo.getRecords()) {
            List<String> permissions = sysRoleMenuMapper.queryPermission(bean.getId());
            for (String permission : permissions) {
                if (StringUtils.isBlank(bean.getPermission())) {
                    bean.setPermission(permission);
                } else {
                    bean.setPermission(bean.getPermission() + ";" + permission);
                }
            }
        }
        return pageInfo;
    }

    /* (non-Javadoc)
     * @see org.ibase4j.provider.SysRoleProvider#getPermissions(java.lang.String) */
    @Override
    public List<String> getPermissions(Long id) {
        return sysRoleMenuMapper.getPermissions(id);
    }
}
