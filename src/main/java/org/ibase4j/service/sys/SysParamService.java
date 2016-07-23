package org.ibase4j.service.sys;

import java.util.Map;

import org.ibase4j.core.base.BaseService;
import org.ibase4j.dao.sys.SysParamExpandMapper;
import org.ibase4j.model.generator.SysParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

/**
 * @author ShenHuaJie
 * @version 2016年5月31日 上午11:01:33
 */
@Service
@CacheConfig(cacheNames = "sysParam")
public class SysParamService extends BaseService<SysParam> {
	@Autowired
	private SysParamExpandMapper sysParamExpandMapper;

	public PageInfo<SysParam> query(Map<String, Object> params) {
		startPage(params);
		return getPage(sysParamExpandMapper.query(params));
	}
}
