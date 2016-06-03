package org.ibase4j.provider.sys;

import java.util.Map;

import org.ibase4j.mybatis.generator.model.SysDic;
import org.ibase4j.mybatis.generator.model.SysDicIndex;

import com.github.pagehelper.PageInfo;

public interface SysDicProvider {

	public void updateDicIndex(SysDicIndex record);

	public void updateDic(SysDic record);

	public void deleteDic(Integer id);

	public SysDicIndex queryDicIndexById(Integer id);

	public SysDic queryDicById(Integer id);

	public PageInfo<SysDicIndex> queryDicIndex(Map<String, Object> params);

	public PageInfo<SysDic> queryDic(Map<String, Object> params);
}
