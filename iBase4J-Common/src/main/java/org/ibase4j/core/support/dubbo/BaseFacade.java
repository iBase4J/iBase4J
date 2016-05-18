package org.ibase4j.core.support.dubbo;

import java.util.Map;

import com.github.pagehelper.PageInfo;

public interface BaseFacade<K> {
	public void update(K record);

	public void delete(Integer id);

	public K queryById(Integer id);

	public PageInfo<K> query(Map<String, Object> params);
}
