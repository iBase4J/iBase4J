package org.ibase4j.service.sys;

import java.util.Map;

import org.ibase4j.core.config.Resources;
import org.ibase4j.mybatis.generator.model.SysDept;
import org.ibase4j.provider.sys.SysDeptProvider;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:16:07
 */
@Service
public class SysDeptService {
	@Reference
	private SysDeptProvider sysDeptFacade;

	public void update(SysDept record) {
		Assert.notNull(record.getId(), Resources.getMessage("ID_IS_NULL"));
		sysDeptFacade.update(record);
	}

	public void add(SysDept record) {
		Assert.notNull(record.getDeptName(), Resources.getMessage("NAME_IS_NULL"));
		sysDeptFacade.update(record);
	}

	public void delete(Integer id) {
		Assert.notNull(id, Resources.getMessage("ID_IS_NULL"));
		sysDeptFacade.delete(id);
	}

	public PageInfo<SysDept> query(Map<String, Object> params) {
		return sysDeptFacade.query(params);
	}

	public SysDept queryById(Integer id) {
		Assert.notNull(id, Resources.getMessage("ID_IS_NULL"));
		return sysDeptFacade.queryById(id);
	}
}
