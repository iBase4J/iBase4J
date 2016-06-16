package org.ibase4j.core.base;

/**
 * @author ShenHuaJie
 * @version 2016年6月3日 下午2:30:14
 */
public interface BaseMapper<T extends BaseModel> {
	int deleteByPrimaryKey(Integer id);

	T selectByPrimaryKey(Integer id);

	int insert(T record);

	int updateByPrimaryKey(T record);
}
