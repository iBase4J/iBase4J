package org.ibase4j.provider.sys;

import org.ibase4j.mybatis.generator.model.SysDic;
import org.ibase4j.mybatis.generator.model.SysDicIndex;

public interface SysDicProvider {

	public void updateDicIndex(SysDicIndex record);

	public void updateDic(SysDic record);

	public void deleteDic(Integer id);

	public SysDicIndex queryDicIndexById(Integer id);

	public SysDic queryDicById(Integer id);
}
