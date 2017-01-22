package org.ibase4j.core.base;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.core.Constants;
import org.ibase4j.core.support.Assert;
import org.ibase4j.core.util.CacheUtil;
import org.ibase4j.core.util.WebUtil;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:47:58
 */
public abstract class BaseService<P extends BaseProvider<T>, T extends BaseModel> {
	protected Logger logger = LogManager.getLogger();
	protected P provider;

	/** 修改 */
	public void update(T record) {
		record.setUpdateBy(WebUtil.getCurrentUser());
		Assert.notNull(record.getId(), "ID");
		provider.update(record);
	}

	/** 新增 */
	public void add(T record) {
		Long uid = WebUtil.getCurrentUser();
		if (record.getCreateBy() == null) {
			record.setCreateBy(uid == null ? 1 : uid);
		}
		if (record.getUpdateBy() == null) {
			record.setUpdateBy(uid == null ? 1 : uid);
		} else if (uid != null) {
			record.setUpdateBy(uid);
		}
		provider.update(record);
	}

	/** 删除 */
	public void delete(Long id) {
		Assert.notNull(id, "ID");
		provider.delete(id, WebUtil.getCurrentUser());
	}

	/** 根据Id查询 */
	@SuppressWarnings("unchecked")
	public T queryById(Long id) {
		Assert.notNull(id, "ID");
		StringBuilder sb = new StringBuilder(Constants.CACHE_NAMESPACE);
		String className = this.getClass().getSimpleName().replace("Service", "");
		sb.append(className.substring(0, 1).toLowerCase()).append(className.substring(1));
		sb.append(":").append(id);
		T record = (T) CacheUtil.getCache().get(sb.toString());
		if (record == null) {
			record = provider.queryById(id);
		}
		return record;
	}

	/** 条件查询 */
	public Page<T> query(Map<String, Object> params) {
		return provider.query(params);
	}
}
