package org.ibase4j.provider.sys;

import java.util.Map;

import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.dao.generator.SysDeptMapper;
import org.ibase4j.dao.sys.SysDeptExpandMapper;
import org.ibase4j.model.generator.SysDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;

import com.github.pagehelper.PageInfo;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@CacheConfig(cacheNames = "sysDept")
@DubboService(interfaceClass = SysDeptProvider.class)
public class SysDeptProviderImpl extends BaseProviderImpl<SysDept> implements SysDeptProvider {
	@Autowired
	private SysDeptMapper sysDeptMapper;
	@Autowired
	private SysDeptExpandMapper syDeptExpandMapper;

	protected Object getMapper() {
		return sysDeptMapper;
	}

	public PageInfo<SysDept> query(Map<String, Object> params) {
		this.startPage(params);
		return getPage(syDeptExpandMapper.query(params));
	}
}
