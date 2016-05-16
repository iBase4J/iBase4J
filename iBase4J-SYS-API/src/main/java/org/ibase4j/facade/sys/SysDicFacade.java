package org.ibase4j.facade.sys;

import org.ibase4j.mybatis.generator.model.SysDic;
import org.ibase4j.mybatis.generator.model.SysDicIndex;

public interface SysDicFacade {

	public void updateDicIndex(SysDicIndex record);

	public void updateDic(SysDic record);

	public void deleteDic(Integer id);

	public SysDicIndex queryDicIndexById(Integer id);

	public SysDic queryDicById(Integer id);
}
