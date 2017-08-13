package org.ibase4j.service;

import java.util.Map;

import org.ibase4j.core.support.Assert;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboReference;
import org.ibase4j.core.util.WebUtil;
import org.ibase4j.model.SysDic;
import org.ibase4j.provider.ISysDicProvider;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;

@Service
public class SysDicService {
	@DubboReference
	private ISysDicProvider provider;

	public Page<SysDic> queryDic(Map<String, Object> params) {
		return provider.queryDic(params);
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

	public SysDic queryDicById(Long id) {
		return provider.queryById(id);
	}

	public Map<String, String> queryDicByDicIndexKey(String key) {
		Assert.notNull(key, "KEY");
		return provider.queryDicByDicIndexKey(key);
	}

}
