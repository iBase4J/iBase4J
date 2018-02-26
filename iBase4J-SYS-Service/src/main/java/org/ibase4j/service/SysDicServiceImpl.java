package org.ibase4j.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.ibase4j.mapper.SysDicMapper;
import org.ibase4j.model.SysDic;
import org.ibase4j.service.ISysDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;

import top.ibase4j.core.base.BaseService;
import top.ibase4j.core.support.context.ApplicationContextHolder;
import top.ibase4j.core.util.InstanceUtil;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@CacheConfig(cacheNames = "SysDic")
@Service(interfaceClass = ISysDicService.class)
public class SysDicServiceImpl extends BaseService<SysDic> implements ISysDicService {
	@Autowired
	private SysDicMapper dicMapper;

	@Transactional
	@CachePut(value = "sysDic")
	public void updateDic(SysDic record) {
		record.setUpdateTime(new Date());
		if (record.getId() == null) {
			record.setCreateTime(new Date());
			dicMapper.insert(record);
		} else {
			dicMapper.updateById(record);
		}
	}

	@Transactional
	@CacheEvict(value = "sysDic")
	public void deleteDic(Long id) {
		dicMapper.deleteById(id);
	}

	@Cacheable(value = "sysDic")
	public SysDic queryDicById(Long id) {
		return dicMapper.selectById(id);
	}

	@Cacheable(value = "sysDics")
	public Map<String, Map<String, String>> getAllDic() {
		Map<String, Object> params = InstanceUtil.newHashMap();
		params.put("orderBy", "type_,sort_no");
		List<SysDic> list = queryList(params);
		Map<String, Map<String, String>> resultMap = InstanceUtil.newHashMap();
		for (SysDic sysDic : list) {
			if (sysDic != null) {
				String key = sysDic.getType();
				if (resultMap.get(key) == null) {
					Map<String, String> dicMap = InstanceUtil.newHashMap();
					resultMap.put(key, dicMap);
				}
				if (StringUtils.isNotBlank(sysDic.getParentCode())) {
					resultMap.get(key).put(sysDic.getParentCode() + sysDic.getCode(), sysDic.getCodeText());
				} else {
					resultMap.get(key).put(sysDic.getCode(), sysDic.getCodeText());
				}
			}
		}
		return resultMap;
	}

	@Cacheable(value = "sysDicMap")
	public Map<String, String> queryDicByDicIndexKey(String key) {
		return ApplicationContextHolder.getBean(ISysDicService.class).getAllDic().get(key);
	}
}
