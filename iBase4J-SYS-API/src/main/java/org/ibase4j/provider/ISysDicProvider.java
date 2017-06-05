package org.ibase4j.provider;

import java.util.Map;

import org.ibase4j.model.SysDic;
import org.ibase4j.model.SysDicIndex;

import com.baomidou.mybatisplus.plugins.Page;

public interface ISysDicProvider {
	public Map<String, Map<String, String>> getAllDic();

	public void updateDicIndex(SysDicIndex record);

	public void updateDic(SysDic record);

	public void deleteDic(Long id);

	public SysDicIndex queryDicIndexById(Long id);

	public SysDic queryDicById(Long id);

	public Page<SysDicIndex> queryDicIndex(Map<String, Object> params);

	public Page<SysDic> queryDic(Map<String, Object> params);

	public void deleteDicIndex(Long id);

	public Map<String, String> queryDicByDicIndexKey(String key);
}
