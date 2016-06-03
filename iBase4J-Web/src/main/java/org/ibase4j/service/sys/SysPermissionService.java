package org.ibase4j.service.sys;

import org.ibase4j.core.Constants;
import org.ibase4j.core.support.BaseService;
import org.ibase4j.core.util.RedisUtil;
import org.ibase4j.mybatis.generator.model.SysPermission;
import org.ibase4j.provider.sys.SysPermissionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:16:13
 */
@Service
public class SysPermissionService extends BaseService<SysPermissionProvider, SysPermission> {
	@Autowired
	public void setProvider(SysPermissionProvider provider) {
		this.provider = provider;
	}

	public Boolean doCheckPermissionByUserId(Integer userId, String url) {
		StringBuilder sb = new StringBuilder(Constants.CACHE_NAMESPACE);
		String className = this.getClass().getSimpleName();
		sb.append(className.substring(0, 1).toLowerCase())
				.append(className.substring(1, className.length() - 7));
		sb.append(":").append(userId).append(",").append(url);
		byte[] value = RedisUtil.get(sb.toString().getBytes());
		if (value != null) {
			return (Boolean) valueSerializer.deserialize(value);
		}
		return provider.doCheckPermissionByUserId(userId, url);
	}
}
