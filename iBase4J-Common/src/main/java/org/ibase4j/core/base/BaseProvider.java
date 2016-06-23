package org.ibase4j.core.base;

import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
public interface BaseProvider<T extends BaseModel> {
	@Transactional
	public T update(T record);

	@Transactional
	public void delete(Integer id, Integer userId);

	public T queryById(Integer id);

	public void init();

	public PageInfo<T> query(Map<String, Object> params);
}
