package org.ibase4j.service;

import java.util.Map;

import org.ibase4j.model.SysDic;

import top.ibase4j.core.base.IBaseService;

public interface ISysDicService extends IBaseService<SysDic> {
	public Map<String, Map<String, String>> getAllDic();

	public void updateDic(SysDic record);

	public void deleteDic(Long id);

	public Map<String, String> queryDicByDicIndexKey(String key);
}
