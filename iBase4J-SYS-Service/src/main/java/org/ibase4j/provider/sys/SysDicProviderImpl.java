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
import org.ibase4j.provider.sys.ISysDicProvider;
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
        if (StringUtils.isBlank(record.getId())) {
            record.setId(createId("SysDicIndex"));
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
        if (StringUtils.isBlank(record.getId())) {
            record.setId(createId("SysDic"));
            record.setCreateTime(new Date());
            dicMapper.insert(record);
        } else {
            dicMapper.updateById(record);
        }
    }

    @Transactional
    @CacheEvict(value = "sysDic")
    public void deleteDic(String id) {
        dicMapper.deleteById(id);
    }

    @Cacheable(value = "sysDicIndex")
    public SysDicIndex queryDicIndexById(String id) {
        return dicIndexMapper.selectById(id);
    }

    @Cacheable(value = "sysDic")
    public SysDic queryDicById(String id) {
        return dicMapper.selectById(id);
    }

    @Cacheable(value = "sysDics")
    public Map<String, Map<String, String>> getAllDic() {
        List<String> records = dicIndexMapper.selectIdByMap(Collections.<String, Object>emptyMap());
        
        Map<String, String> dicIndexMap = InstanceUtil.newHashMap();
        for (String id : records) {
            dicIndexMap.put(id, dicIndexMapper.selectById(id).getKeyValue());
        }
        records = dicMapper.selectIdByMap(Collections.<String, Object>emptyMap());
        Page<String> idPage = new Page<String>(1, records.size());
        idPage.setRecords(records);
        Page<SysDic> sysDics = getPage(idPage, SysDic.class);
        Map<String, Map<String, String>> resultMap = InstanceUtil.newHashMap();
        for (SysDic sysDic : sysDics.getRecords()) {
            String key = dicIndexMap.get(sysDic.getIndexId());
            if (resultMap.get(key) == null) {
                Map<String, String> dicMap = InstanceUtil.newHashMap();
                resultMap.put(key, dicMap);
            }
            resultMap.get(key).put(sysDic.getCode(), sysDic.getCodeText());
        }
        return resultMap;
    }

    @Cacheable(value = "sysDicMap")
    public Map<String, String> queryDicByDicIndexKey(String key) {
        return InstanceUtil.getBean(ISysDicProvider.class).getAllDic().get(key);
    }

    public Page<SysDicIndex> queryDicIndex(Map<String, Object> params) {
        Page<String> idPage = this.getPage(params);
        List<String> ids = dicIndexMapper.selectIdByMap(idPage, params);
        Page<SysDicIndex> page = new Page<SysDicIndex>(idPage.getCurrent(), idPage.getSize());
        page.setTotal(idPage.getTotal());
        if (ids != null) {
            ISysDicProvider provider = InstanceUtil.getBean(getClass());
            List<SysDicIndex> records = InstanceUtil.newArrayList();
            for (String id : ids) {
                records.add(provider.queryDicIndexById(id));
            }
            page.setRecords(records);
        }
        return page;
    }

    public Page<SysDic> queryDic(Map<String, Object> params) {
        Page<String> page = this.getPage(params);
        page.setRecords(mapper.selectIdByMap(page, params));
        return getPage(page);
    }

    public void deleteDicIndex(String id) {
        Map<String, Object> params = InstanceUtil.newHashMap();
        params.put("index_id", id);
        List<String> ids = dicMapper.selectIdByMap(params);
        if (ids.size() > 0) {
            throw new BusinessException();
        }
        dicIndexMapper.deleteById(id);
    }
}
