package org.ibase4j.core.support;

import java.util.Map;

import org.ibase4j.core.config.Resources;
import org.ibase4j.core.support.dubbo.BaseProvider;
import org.springframework.util.Assert;

import com.github.pagehelper.PageInfo;

public abstract class BaseService<P extends BaseProvider<T>, T> {
	protected P provider;

	public void setProvider(P provider) {
		this.provider = provider;
	}

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

	public PageInfo<T> query(Map<String, Object> params) {
		return provider.query(params);
	}

	public T queryById(Integer id) {
		Assert.notNull(id, Resources.getMessage("ID_IS_NULL"));
		return provider.queryById(id);
	}
}
