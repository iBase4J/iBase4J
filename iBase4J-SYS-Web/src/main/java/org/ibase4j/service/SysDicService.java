package org.ibase4j.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.ibase4j.core.support.Assert;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboReference;
import org.ibase4j.core.util.WebUtil;
import org.ibase4j.model.SysDic;
import org.ibase4j.model.SysDicIndex;
import org.ibase4j.provider.ISysDicProvider;

import com.baomidou.mybatisplus.plugins.Page;

@Service
public class SysDicService {
	@DubboReference
	private ISysDicProvider provider;

	public Page<SysDicIndex> queryDicIndex(Map<String, Object> params) {
		return provider.queryDicIndex(params);
	}

	public Page<SysDic> queryDic(Map<String, Object> params) {
		return provider.queryDic(params);
	}

	public void addDicIndex(SysDicIndex record) {
		record.setCreateBy(WebUtil.getCurrentUser());
		provider.updateDicIndex(record);
	}

	public void updateDicIndex(SysDicIndex record) {
		Assert.notNull(record.getId(), "ID");
		record.setUpdateBy(WebUtil.getCurrentUser());
		provider.updateDicIndex(record);
	}

	public void deleteDicIndex(Long id) {
		Assert.notNull(id, "ID");
		provider.deleteDicIndex(id);
	}

	public void addDic(SysDic record) {
		record.setCreateBy(WebUtil.getCurrentUser());
		provider.updateDic(record);
	}

	public void updateDic(SysDic record) {
		Assert.notNull(record.getId(), "ID");
		record.setUpdateBy(WebUtil.getCurrentUser());
		provider.updateDic(record);
	}

	public void deleteDic(Long id) {
		Assert.notNull(id, "ID");
		provider.deleteDic(id);
	}

	public SysDicIndex queryDicIndexById(Long id) {
		return provider.queryDicIndexById(id);
	}

	public SysDic queryDicById(Long id) {
		return provider.queryDicById(id);
	}

	public Map<String, String> queryDicByDicIndexKey(String key) {
		Assert.notNull(key, "KEY");
		return provider.queryDicByDicIndexKey(key);
	}

}
