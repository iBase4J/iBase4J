package org.ibase4j.provider.sys;

import java.util.Map;

import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.dao.generator.SysParamMapper;
import org.ibase4j.dao.sys.SysParamExpandMapper;
import org.ibase4j.model.generator.SysParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;

import com.github.pagehelper.PageInfo;

/**
 * @author ShenHuaJie
 * @version 2016年5月31日 上午11:01:33
 */
@CacheConfig(cacheNames = "sysParam")
@DubboService(interfaceClass = SysParamProvider.class)
public class SysParamProviderImpl extends BaseProviderImpl<SysParam> implements SysParamProvider {
	@Autowired
	private SysParamMapper sysParamMapper;
	@Autowired
	private SysParamExpandMapper sysParamExpandMapper;

	protected Object getMapper() {
		return sysParamMapper;
	}

	public PageInfo<SysParam> query(Map<String, Object> params) {
		startPage(params);
		return getPage(sysParamExpandMapper.query(params));
	}
}
