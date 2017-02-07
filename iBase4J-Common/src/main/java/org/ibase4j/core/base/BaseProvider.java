package org.ibase4j.core.base;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
public interface BaseProvider<T extends BaseModel> {
	@Transactional
	public T update(T record);

	@Transactional
	public void del(Long id, Long userId);

	@Transactional
	public void delete(Long id);

	public T queryById(Long id);

	public Page<T> query(Map<String, Object> params);

	public Page<Map<String, Object>> queryMap(Map<String, Object> params);

	public <K> Page<K> getPage(Page<Long> ids, Class<K> cls);

	public <K> List<K> getList(List<Long> ids, Class<K> cls);
}
