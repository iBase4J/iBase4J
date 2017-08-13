package org.ibase4j.provider;

import java.util.Map;

import org.ibase4j.core.base.BaseProvider;
import org.ibase4j.model.SysDic;

import com.baomidou.mybatisplus.plugins.Page;

public interface ISysDicProvider extends BaseProvider<SysDic> {
	public Map<String, Map<String, String>> getAllDic();

	public void updateDic(SysDic record);

	public void deleteDic(Long id);

	public Page<SysDic> queryDic(Map<String, Object> params);

	public Map<String, String> queryDicByDicIndexKey(String key);
}
