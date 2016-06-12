package org.ibase4j.service.sys;

import java.util.Map;

import org.ibase4j.core.config.Resources;
import org.ibase4j.core.support.Assert;
import org.ibase4j.model.generator.SysDic;
import org.ibase4j.model.generator.SysDicIndex;
import org.ibase4j.provider.sys.SysDicProvider;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;

@Service
public class SysDicService {
	@Reference
	private SysDicProvider provider;

	public PageInfo<SysDicIndex> queryDicIndex(Map<String, Object> params) {
		return provider.queryDicIndex(params);
	}

	public PageInfo<SysDic> queryDic(Map<String, Object> params) {
		return provider.queryDic(params);
	}

	public void addDicIndex(SysDicIndex record) {
		provider.updateDicIndex(record);
	}

	public void updateDicIndex(SysDicIndex record) {
		Assert.notNull(record.getId(), Resources.getMessage("ID_IS_NULL"));
		provider.updateDicIndex(record);
	}

	public void deleteDicIndex(Integer id) {
		Assert.notNull(id, Resources.getMessage("ID_IS_NULL"));
		provider.deleteDicIndex(id);
	}

	public void addDic(SysDic record) {
		provider.updateDic(record);
	}

	public void updateDic(SysDic record) {
		Assert.notNull(record.getId(), Resources.getMessage("ID_IS_NULL"));
		provider.updateDic(record);
	}

	public void deleteDic(Integer id) {
		Assert.notNull(id, Resources.getMessage("ID_IS_NULL"));
		provider.deleteDic(id);
	}

	public SysDicIndex queryDicIndexById(Integer id) {
		return provider.queryDicIndexById(id);
	}

	public SysDic queryDicById(Integer id) {
		return provider.queryDicById(id);
	}
}
