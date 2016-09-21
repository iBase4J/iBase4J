package org.ibase4j.provider.sys.impl;

import java.util.Map;

import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.model.sys.SysDept;
import org.ibase4j.provider.sys.ISysDeptProvider;
import org.springframework.cache.annotation.CacheConfig;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@CacheConfig(cacheNames = "sysDept")
@DubboService(interfaceClass = ISysDeptProvider.class)
public class SysDeptProviderImpl extends BaseProviderImpl<SysDept> implements ISysDeptProvider {

    public Page<SysDept> query(Map<String, Object> params) {
        Page<String> page = this.getPage(params);
        page.setRecords(mapper.selectIdByMap(page, params));
        return getPage(page);
    }
}
