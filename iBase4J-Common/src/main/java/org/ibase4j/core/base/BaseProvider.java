package org.ibase4j.core.base;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
public interface BaseProvider<T extends BaseModel> {
	@Transactional
	public T update(T record);

	@Transactional
	public void delete(String id, String userId);

	public T queryById(String id);

	public void init();

	public PageInfo<T> query(Map<String, Object> params);
	
    public <K> PageInfo<K> getPage(Page<String> ids, Class<K> cls);

    public <K> List<K> getList(List<String> ids, Class<K> cls);
}
