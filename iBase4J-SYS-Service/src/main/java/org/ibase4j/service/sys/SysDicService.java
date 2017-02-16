package org.ibase4j.service.sys;

import java.util.List;
import java.util.Map;

import org.ibase4j.core.base.BaseService;
import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.model.sys.SysDic;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@Service
@CacheConfig(cacheNames = "SysDic")
public class SysDicService extends BaseService<SysDic> {

	@Cacheable(value = "sysDics")
	public Map<String, Map<String, String>> getAllDic() {
		Map<String, Object> params = InstanceUtil.newHashMap();
		params.put("orderBy", "sort_no");
		List<SysDic> list = queryList(params);
		Map<String, Map<String, String>> resultMap = InstanceUtil.newHashMap();
		for (SysDic sysDic : list) {
			String key = sysDic.getType();
			if (resultMap.get(key) == null) {
				Map<String, String> dicMap = InstanceUtil.newHashMap();
				resultMap.put(key, dicMap);
			}
			resultMap.get(key).put(sysDic.getCode(), sysDic.getCodeText());
		}
		return resultMap;
	}

	@Cacheable(value = "sysDicMap")
	public Map<String, String> queryDicByType(String key) {
		return applicationContext.getBean(SysDicService.class).getAllDic().get(key);
	}
}
