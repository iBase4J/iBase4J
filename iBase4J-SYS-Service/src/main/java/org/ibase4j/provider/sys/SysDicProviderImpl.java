package org.ibase4j.provider.sys;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.core.exception.BusinessException;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.dao.sys.SysDicIndexMapper;
import org.ibase4j.dao.sys.SysDicMapper;
import org.ibase4j.model.sys.SysDic;
import org.ibase4j.model.sys.SysDicIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@CacheConfig(cacheNames = "SysDic")
@DubboService(interfaceClass = ISysDicProvider.class)
public class SysDicProviderImpl extends BaseProviderImpl<SysDic> implements ISysDicProvider {
    @Autowired
    private SysDicMapper dicMapper;
    @Autowired
    private SysDicIndexMapper dicIndexMapper;

    @Transactional
    @CachePut(value = "sysDicIndex")
    public void updateDicIndex(SysDicIndex record) {
        record.setUpdateTime(new Date());
        if (record.getId() == null) {
            record.setCreateTime(new Date());
            dicIndexMapper.insert(record);
        } else {
            dicIndexMapper.updateById(record);
        }
    }

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

    @Cacheable(value = "sysDicIndex")
    public SysDicIndex queryDicIndexById(Long id) {
        return dicIndexMapper.selectById(id);
    }

    @Cacheable(value = "sysDic")
    public SysDic queryDicById(Long id) {
        return dicMapper.selectById(id);
    }

    @Cacheable(value = "sysDics")
    public Map<String, Map<String, String>> getAllDic() {
        List<Long> records = dicIndexMapper.selectIdByMap(Collections.<String, Object>emptyMap());
        
        Map<Long, String> dicIndexMap = InstanceUtil.newHashMap();
        for (Long id : records) {
            dicIndexMap.put(id, dicIndexMapper.selectById(id).getKeyValue());
        }
        records = dicMapper.selectIdByMap(Collections.<String, Object>emptyMap());
        Page<Long> idPage = new Page<Long>(1, records.size());
        idPage.setRecords(records);
        Page<SysDic> sysDics = getPage(idPage, SysDic.class);
        Map<String, Map<String, String>> resultMap = InstanceUtil.newHashMap();
        for (SysDic sysDic : sysDics.getRecords()) {
            String key = dicIndexMap.get(sysDic.getIndexId());
            if (StringUtils.isNotBlank(key)) {
                if (resultMap.get(key) == null) {
                    Map<String, String> dicMap = InstanceUtil.newHashMap();
                    resultMap.put(key, dicMap);
                }
                resultMap.get(key).put(sysDic.getCode(), sysDic.getCodeText());
            }
        }
        return resultMap;
    }

    @Cacheable(value = "sysDicMap")
    public Map<String, String> queryDicByDicIndexKey(String key) {
        return InstanceUtil.getBean(ISysDicProvider.class).getAllDic().get(key);
    }

    public Page<SysDicIndex> queryDicIndex(Map<String, Object> params) {
        Page<Long> idPage = getPage(params);
        List<Long> ids = dicIndexMapper.selectIdByMap(idPage, params);
        Page<SysDicIndex> page = new Page<SysDicIndex>(idPage.getCurrent(), idPage.getSize());
        page.setTotal(idPage.getTotal());
        if (ids != null) {
            ISysDicProvider provider = InstanceUtil.getBean(getClass());
            List<SysDicIndex> records = InstanceUtil.newArrayList();
            for (Long id : ids) {
                records.add(provider.queryDicIndexById(id));
            }
            page.setRecords(records);
        }
        return page;
    }

    public Page<SysDic> queryDic(Map<String, Object> params) {
        Page<Long> page = getPage(params);
        page.setRecords(mapper.selectIdByMap(page, params));
        return getPage(page);
    }

    public void deleteDicIndex(Long id) {
        Map<String, Object> params = InstanceUtil.newHashMap();
        params.put("index_id", id);
        List<Long> ids = dicMapper.selectIdByMap(params);
        if (ids.size() > 0) {
            throw new BusinessException();
        }
        dicIndexMapper.deleteById(id);
    }
}
