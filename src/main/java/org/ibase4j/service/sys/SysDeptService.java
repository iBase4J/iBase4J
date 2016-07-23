package org.ibase4j.service.sys;

import java.util.Map;

import org.ibase4j.core.base.BaseService;
import org.ibase4j.dao.sys.SysDeptExpandMapper;
import org.ibase4j.model.generator.SysDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@CacheConfig(cacheNames = "sysDept")
@Service
public class SysDeptService extends BaseService<SysDept> {
	@Autowired
	private SysDeptExpandMapper syDeptExpandMapper;

	public PageInfo<SysDept> query(Map<String, Object> params) {
		this.startPage(params);
		return getPage(syDeptExpandMapper.query(params));
	}
}
