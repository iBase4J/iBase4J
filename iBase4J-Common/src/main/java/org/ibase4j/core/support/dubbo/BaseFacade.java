package org.ibase4j.core.support.dubbo;

import java.util.Map;

import com.github.pagehelper.PageInfo;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
public interface BaseFacade<K> {
	public K update(K record);

	public void delete(Integer id);

	public K queryById(Integer id);

	public PageInfo<K> query(Map<String, Object> params);
}
