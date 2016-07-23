package org.ibase4j.service.sys;

import java.util.List;
import java.util.Map;

import org.ibase4j.core.base.BaseService;
import org.ibase4j.core.exception.BusinessException;
import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.core.util.WebUtil;
import org.ibase4j.dao.generator.SysDicIndexMapper;
import org.ibase4j.dao.generator.SysDicMapper;
import org.ibase4j.dao.sys.SysDicExpandMapper;
import org.ibase4j.model.generator.SysDic;
import org.ibase4j.model.generator.SysDicIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

@Service
public class SysDicService extends BaseService<SysDic> {
    @Autowired
    private SysDicMapper dicMapper;
    @Autowired
    private SysDicIndexMapper dicIndexMapper;
    @Autowired
    private SysDicExpandMapper dicExpandMapper;

    @Transactional
    @CachePut(value = "sysDicIndex")
    public void updateDicIndex(SysDicIndex record) {
        record.setUpdateBy(WebUtil.getCurrentUser());
        if (record.getId() == null) {
            record.setCreateBy(WebUtil.getCurrentUser());
            dicIndexMapper.insert(record);
        } else {
            dicIndexMapper.updateByPrimaryKey(record);
        }
    }

    @Transactional
    @CachePut(value = "sysDic")
    public void updateDic(SysDic record) {
        record.setUpdateBy(WebUtil.getCurrentUser());
        if (record.getId() == null) {
            record.setCreateBy(WebUtil.getCurrentUser());
            dicMapper.insert(record);
        } else {
            dicMapper.updateByPrimaryKey(record);
        }
    }

    @Transactional
    @CacheEvict(value = "sysDic")
    public void deleteDic(Integer id) {
        dicMapper.deleteByPrimaryKey(id);
    }

    @Cacheable(value = "sysDicIndex")
    public SysDicIndex queryDicIndexById(Integer id) {
        return dicIndexMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value = "sysDic")
    public SysDic queryDicById(Integer id) {
        return dicMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value = "sysDics")
    public Map<String, Map<String, String>> getAllDic() {
        List<SysDicIndex> sysDicIndexs = dicIndexMapper.selectAll();
        Map<Integer, String> dicIndexMap = InstanceUtil.newHashMap();
        for (SysDicIndex sysDicIndex : sysDicIndexs) {
            dicIndexMap.put(sysDicIndex.getId(), sysDicIndex.getKey());
        }
        List<SysDic> sysDics = dicMapper.selectAll();
        Map<String, Map<String, String>> resultMap = InstanceUtil.newHashMap();
        for (SysDic sysDic : sysDics) {
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
        return InstanceUtil.getBean(SysDicService.class).getAllDic().get(key);
    }

    public PageInfo<SysDicIndex> queryDicIndex(Map<String, Object> params) {
        startPage(params);
        Page<Integer> ids = dicExpandMapper.queryDicIndex(params);
        Page<SysDicIndex> page = new Page<SysDicIndex>(ids.getPageNum(), ids.getPageSize());
        page.setTotal(ids.getTotal());
        if (ids != null) {
            page.clear();
            SysDicService service = InstanceUtil.getBean(getClass());
            for (Integer id : ids) {
                page.add(service.queryDicIndexById(id));
            }
        }
        return new PageInfo<SysDicIndex>(page);
    }

    public PageInfo<SysDic> queryDic(Map<String, Object> params) {
        startPage(params);
        return getPage(dicExpandMapper.queryDic(params));
    }

    public void deleteDicIndex(Integer id) {
        Map<String, Object> params = InstanceUtil.newHashMap();
        params.put("index_id", id);
        List<Integer> ids = dicExpandMapper.queryDic(params);
        if (ids.size() > 0) {
            throw new BusinessException();
        }
        dicIndexMapper.deleteByPrimaryKey(id);
    }
}
