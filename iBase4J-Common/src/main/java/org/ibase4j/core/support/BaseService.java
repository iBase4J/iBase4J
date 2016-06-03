package org.ibase4j.core.support;

import java.util.Map;

import org.ibase4j.core.Constants;
import org.ibase4j.core.config.Resources;
import org.ibase4j.core.support.dubbo.BaseProvider;
import org.ibase4j.core.support.spring.data.redis.ObjectRedisSerializer;
import org.ibase4j.core.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.github.pagehelper.PageInfo;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:47:58
 */
public abstract class BaseService<P extends BaseProvider<T>, T> {
	protected P provider;
	@Autowired
	protected ObjectRedisSerializer valueSerializer;

	/** 修改 */
	public void update(T record) {
		Object id = null;
		try {
			id = record.getClass().getDeclaredMethod("getId").invoke(record);
		} catch (Exception e) {
		}
		Assert.notNull(id, Resources.getMessage("ID_IS_NULL"));
		provider.update(record);
	}

	/** 新增 */
	public void add(T record) {
		provider.update(record);
	}

	/** 删除 */
	public void delete(Integer id) {
		Assert.notNull(id, Resources.getMessage("ID_IS_NULL"));
		provider.delete(id);
	}

	/** 根据Id查询 */
	@SuppressWarnings("unchecked")
	public T queryById(Integer id) {
		Assert.notNull(id, Resources.getMessage("ID_IS_NULL"));
		StringBuilder sb = new StringBuilder(Constants.CACHE_NAMESPACE);
		String className = this.getClass().getSimpleName();
		sb.append(className.substring(0, 1).toLowerCase()).append(className.substring(1, className.length() - 7));
		sb.append(":").append(id);
		byte[] value = RedisUtil.get(sb.toString().getBytes());
		if (value != null) {
			return (T) valueSerializer.deserialize(value);
		}
		return provider.queryById(id);
	}

	/** 条件查询 */
	public PageInfo<T> query(Map<String, Object> params) {
		return provider.query(params);
	}
}
