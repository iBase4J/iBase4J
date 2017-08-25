/**
 * 
 */
package org.ibase4j.core.config;

import java.lang.reflect.Method;

import org.apache.commons.lang3.ArrayUtils;
import org.ibase4j.core.Constants;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSON;

/**
 * Redis缓存配置
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:18:41
 */
@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {
	String prefix = Constants.CACHE_NAMESPACE + "M:";

	@Bean
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			/** 重写生成key方法 */
			public Object generate(Object o, Method method, Object... objects) {
				StringBuilder sb = new StringBuilder(prefix);
				CacheConfig cacheConfig = o.getClass().getAnnotation(CacheConfig.class);
				Cacheable cacheable = method.getAnnotation(Cacheable.class);
				CachePut cachePut = method.getAnnotation(CachePut.class);
				CacheEvict cacheEvict = method.getAnnotation(CacheEvict.class);
				if (cacheable != null) {
					String[] cacheNames = cacheable.value();
					if (ArrayUtils.isNotEmpty(cacheNames)) {
						sb.append(cacheNames[0]);
					}
				} else if (cachePut != null) {
					String[] cacheNames = cachePut.value();
					if (ArrayUtils.isNotEmpty(cacheNames)) {
						sb.append(cacheNames[0]);
					}
				} else if (cacheEvict != null) {
					String[] cacheNames = cacheEvict.value();
					if (ArrayUtils.isNotEmpty(cacheNames)) {
						sb.append(cacheNames[0]);
					}
				}
				if (cacheConfig != null && sb.toString().equals(prefix)) {
					String[] cacheNames = cacheConfig.cacheNames();
					if (ArrayUtils.isNotEmpty(cacheNames)) {
						sb.append(cacheNames[0]);
					}
				}
				if (sb.toString().equals(prefix)) {
					sb.append(o.getClass().getName()).append(".").append(method.getName());
				}
				sb.append(":");
				if (objects != null) {
					for (Object object : objects) {
						sb.append(JSON.toJSONString(object));
					}
				}
				return sb.toString();
			}
		};
	}
}
