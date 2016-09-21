package org.ibase4j.core.base;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.core.Constants;
import org.ibase4j.core.support.Assert;
import org.ibase4j.core.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.baomidou.mybatisplus.plugins.Page;


/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:47:58
 */
public abstract class BaseService<P extends BaseProvider<T>, T extends BaseModel> {
    protected Logger logger = LogManager.getLogger();
    protected P provider;
    @Autowired
    private RedisTemplate<Serializable, Serializable> redisTemplate;

    /** 修改 */
    public void update(T record) {
        record.setUpdateBy(WebUtil.getCurrentUser());
        Assert.isNotBlank(record.getId(), "ID");
        provider.update(record);
    }

    /** 新增 */
    public void add(T record) {
        String uid = WebUtil.getCurrentUser();
        if (StringUtils.isBlank(record.getCreateBy())) {
            record.setCreateBy(uid == null ? "" : uid);
        }
        if (StringUtils.isBlank(record.getUpdateBy())) {
            record.setUpdateBy(uid == null ? "" : uid);
        } else if (StringUtils.isNotBlank(uid)) {
            record.setUpdateBy(uid);
        }
        provider.update(record);
    }

    /** 删除 */
    public void delete(String id) {
        Assert.isNotBlank(id, "ID");
        provider.delete(id, WebUtil.getCurrentUser());
    }

    /** 根据Id查询 */
    @SuppressWarnings("unchecked")
    public T queryById(String id) {
        Assert.isNotBlank(id, "ID");
        StringBuilder sb = new StringBuilder(Constants.CACHE_NAMESPACE);
        String className = this.getClass().getSimpleName().replace("Service", "");
        sb.append(className.substring(0, 1).toLowerCase()).append(className.substring(1));
        sb.append(":").append(id);
        T record = (T)redisTemplate.opsForValue().get(sb.toString());
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
