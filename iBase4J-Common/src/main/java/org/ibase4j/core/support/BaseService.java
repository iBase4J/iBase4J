package org.ibase4j.core.support;

import java.util.Map;

import org.ibase4j.core.config.Resources;
import org.ibase4j.core.support.dubbo.BaseProvider;
import org.ibase4j.core.support.spring.data.redis.ObjectRedisSerializer;
import org.ibase4j.core.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.github.pagehelper.PageInfo;

public abstract class BaseService<P extends BaseProvider<T>, T> {
	protected P provider;
	@Autowired
	private ObjectRedisSerializer valueSerializer;

	public void update(T record) {
		Object id = null;
		try {
			id = record.getClass().getDeclaredMethod("getId").invoke(record);
		} catch (Exception e) {
		}
		Assert.notNull(id, Resources.getMessage("ID_IS_NULL"));
		provider.update(record);
	}

	public void add(T record) {
		provider.update(record);
	}

	public void delete(Integer id) {
		Assert.notNull(id, Resources.getMessage("ID_IS_NULL"));
		provider.delete(id);
	}

	@SuppressWarnings("unchecked")
	public T queryById(Integer id) {
		Assert.notNull(id, Resources.getMessage("ID_IS_NULL"));
		StringBuilder sb = new StringBuilder("iBase4J:");
		String className = this.getClass().getSimpleName();
		sb.append(className.substring(0, 1).toLowerCase()).append(className.substring(1, className.length() - 7));
		sb.append(":").append(id);
		byte[] value = RedisUtil.get(sb.toString().getBytes());
		if (value != null) {
			return (T) valueSerializer.deserialize(value);
		}
		return provider.queryById(id);
	}

	public PageInfo<T> query(Map<String, Object> params) {
		return provider.query(params);
	}
}
