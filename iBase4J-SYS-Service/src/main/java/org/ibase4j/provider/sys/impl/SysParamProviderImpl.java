package org.ibase4j.provider.sys.impl;

import java.util.Map;

import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.model.sys.SysParam;
import org.ibase4j.provider.sys.ISysParamProvider;
import org.springframework.cache.annotation.CacheConfig;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * @author ShenHuaJie
 * @version 2016年5月31日 上午11:01:33
 */
@CacheConfig(cacheNames = "sysParam")
@DubboService(interfaceClass = ISysParamProvider.class)
public class SysParamProviderImpl extends BaseProviderImpl<SysParam> implements ISysParamProvider {

	public Page<SysParam> query(Map<String, Object> params) {
        Page<String> page = this.getPage(params);
        page.setRecords(mapper.selectIdByMap(page, params));
        return getPage(page);
	}
}
