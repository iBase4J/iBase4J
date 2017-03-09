package org.ibase4j.service;

import org.ibase4j.core.base.BaseService;
import org.ibase4j.model.SysDept;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@Service
@CacheConfig(cacheNames = "sysDept")
public class SysDeptService extends BaseService<SysDept> {
	public SysDept queryById(Long id) {
		SysDept sysDept = super.queryById(id);
		if (sysDept != null) {
			if (sysDept.getParentId() != null) {
				SysDept parent = super.queryById(sysDept.getParentId());
				if (parent != null) {
					sysDept.setParentName(parent.getDeptName());
				} else {
					sysDept.setParentId(null);
				}
			}
		}
		return sysDept;
	}
}
