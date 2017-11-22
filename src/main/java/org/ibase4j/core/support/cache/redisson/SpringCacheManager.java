package org.ibase4j.core.support.cache.redisson;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.redisson.api.RMap;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonCache;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class SpringCacheManager implements CacheManager, ResourceLoaderAware, InitializingBean {
	private ResourceLoader resourceLoader;
	private boolean allowNullValues;
	private Codec codec;
	private RedissonClient redisson;
	private Map<String, CacheConfig> configMap = new LinkedHashMap<String, CacheConfig>();
	private Map<String, CacheConfig> patternMap = new LinkedHashMap<String, CacheConfig>();
	private String configLocation;
	private String regExpConfigLocation;

	public SpringCacheManager() {
	}

	/**
	 * Creates CacheManager supplied by Redisson instance and Cache config
	 * mapped by Cache name
	 *
	 * @param redisson
	 *            object
	 * @param config
	 *            object
	 */
	public SpringCacheManager(RedissonClient redisson, Map<String, CacheConfig> config) {
		this(redisson, config, null, null);
	}

	/**
	 * Creates CacheManager supplied by Redisson instance, Codec instance and
	 * Cache config mapped by Cache name.
	 * <p>
	 * Each Cache instance share one Codec instance.
	 *
	 * @param redisson
	 *            object
	 * @param config
	 *            object
	 * @param codec
	 *            object
	 */
	public SpringCacheManager(RedissonClient redisson, Map<String, CacheConfig> config, Codec codec) {
		this(redisson, config, null, codec);
	}

	/**
	 * Creates CacheManager supplied by Redisson instance and Cache config
	 * mapped by Cache name and RegExp config mapped by pattern.
	 *
	 * @param redisson
	 *            object
	 * @param config
	 *            object
	 * @param patternConfig
	 */
	public SpringCacheManager(RedissonClient redisson, Map<String, CacheConfig> config,
			Map<String, CacheConfig> patternConfig) {
		this(redisson, config, patternConfig, null);
	}

	/**
	 * Creates CacheManager supplied by Redisson instance, Codec instance, Cache
	 * config mapped by Cache name and RegExp config mapped by pattern.
	 * <p>
	 * Each Cache instance share one Codec instance.
	 *
	 * @param redisson
	 *            object
	 * @param config
	 *            object
	 * @param patternConfig
	 * @param codec
	 *            object
	 */
	public SpringCacheManager(RedissonClient redisson, Map<String, CacheConfig> config,
			Map<String, CacheConfig> patternConfig, Codec codec) {
		this.redisson = redisson;
		if (config != null) {
			this.configMap.putAll(config);
		}
		if (patternConfig != null) {
			this.patternMap.putAll(patternConfig);
		}
		this.codec = codec;
	}

	/**
	 * Creates CacheManager supplied by Redisson instance and Cache config
	 * mapped by Cache name.
	 * <p>
	 * Loads the config file from the class path, interpreting plain paths as
	 * class path resource names that include the package path (e.g.
	 * "mypackage/myresource.txt").
	 *
	 * @param redisson
	 *            object
	 * @param configLocation
	 *            path
	 */
	public SpringCacheManager(RedissonClient redisson, String configLocation) {
		this(redisson, configLocation, null, null);
	}

	/**
	 * Creates CacheManager supplied by Redisson instance, Cache config location
	 * path and RegExp pattern config location path.
	 * <p>
	 * Loads the config file from the class path, interpreting plain paths as
	 * class path resource names that include the package path (e.g.
	 * "mypackage/myresource.txt").
	 *
	 * @param redisson
	 *            object
	 * @param configLocation
	 *            path
	 * @param regExpConfigLocation
	 */
	public SpringCacheManager(RedissonClient redisson, String configLocation, String regExpConfigLocation) {
		this(redisson, configLocation, regExpConfigLocation, null);
	}

	/**
	 * Creates CacheManager supplied by Redisson instance, Codec instance and
	 * Config location path.
	 * <p>
	 * Each Cache instance share one Codec instance.
	 * <p>
	 * Loads the config file from the class path, interpreting plain paths as
	 * class path resource names that include the package path (e.g.
	 * "mypackage/myresource.txt").
	 *
	 * @param redisson
	 *            object
	 * @param configLocation
	 *            path
	 * @param codec
	 *            object
	 */
	public SpringCacheManager(RedissonClient redisson, String configLocation, Codec codec) {
		this(redisson, configLocation, null, codec);
	}

	/**
	 * Creates CacheManager supplied by Redisson instance, Codec instance,
	 * Config location path and RegExp config location path.
	 * <p>
	 * Each Cache instance share one Codec instance.
	 * <p>
	 * Loads the config file from the class path, interpreting plain paths as
	 * class path resource names that include the package path (e.g.
	 * "mypackage/myresource.txt").
	 *
	 * @param redisson
	 *            object
	 * @param configLocation
	 *            path
	 * @param regExpConfigLocation
	 * @param codec
	 *            object
	 */
	public SpringCacheManager(RedissonClient redisson, String configLocation, String regExpConfigLocation,
			Codec codec) {
		this.allowNullValues = true;
		this.redisson = redisson;
		this.configLocation = configLocation;
		this.regExpConfigLocation = regExpConfigLocation;
		this.codec = codec;
	}

	/**
	 * Set cache config location
	 *
	 * @param configLocation
	 *            object
	 */
	public void setConfigLocation(String configLocation) {
		this.configLocation = configLocation;
	}

	/**
	 * Set RegExp pattern config location
	 *
	 * @param regExpConfigLocation
	 *            object
	 */
	public void setRegExpConfigLocation(String regExpConfigLocation) {
		this.regExpConfigLocation = regExpConfigLocation;
	}

	/**
	 * Set cache config mapped by cache name
	 *
	 * @param config
	 *            object
	 */
	public void setConfig(Map<String, ? extends CacheConfig> config) {
		this.configMap.clear();
		if (config == null) {
			return;
		}
		this.configMap.putAll(config);
	}

	/**
	 * Set cache regexp config mapped by pattern
	 *
	 * @param config
	 *            object
	 */
	public void setPatternConfig(Map<String, CacheConfig> config) {
		this.patternMap.clear();
		if (config == null) {
			return;
		}
		this.patternMap.putAll(config);
	}

	/**
	 * Set Redisson instance
	 *
	 * @param redisson
	 *            instance
	 */
	public void setRedisson(RedissonClient redisson) {
		this.redisson = redisson;
	}

	/**
	 * Set Codec instance shared between all Cache instances
	 *
	 * @param codec
	 *            object
	 */
	public void setCodec(Codec codec) {
		this.codec = codec;
	}

	public void setAllowNullValues(boolean allowNullValues) {
		this.allowNullValues = allowNullValues;
	}

	@Override
	public Cache getCache(String name) {
		CacheConfig config = configMap.get(name);
		final String cacheName;
		if (config == null) {
			Pattern pattern = testRegExp(name);
			if (pattern == null) {
				config = new CacheConfig();
				configMap.put(name, config);

				RMap<Object, Object> map = createMap(name);
				return new RedissonCache(map, this.allowNullValues);
			} else {
				config = patternMap.get(pattern);
				cacheName = pattern.pattern();
			}
		} else {
			cacheName = name;
		}
		if (config.getMaxIdleTime() == 0 && config.getTTL() == 0) {
			RMap<Object, Object> map = createMap(cacheName);
			return new RedissonCache(map, this.allowNullValues);
		}
		RMapCache<Object, Object> map = createMapCache(cacheName);
		return new RedissonCache(map, config, this.allowNullValues);
	}

	private Pattern testRegExp(String name) {
		for (String regex : patternMap.keySet()) {
			Pattern pattern = Pattern.compile(regex);
			if (pattern.matcher(name).matches()) {
				return pattern;
			}
		}
		return null;
	}

	private RMap<Object, Object> createMap(String name) {
		if (codec != null) {
			return redisson.getMap(name, codec);
		}
		return redisson.getMap(name);
	}

	private RMapCache<Object, Object> createMapCache(String name) {
		if (codec != null) {
			return redisson.getMapCache(name, codec);
		}
		return redisson.getMapCache(name);
	}

	@Override
	public Collection<String> getCacheNames() {
		Set<String> names = Collections.emptySet();
		names.addAll(getConfigNames());
		names.addAll(getPatternNames());
		return Collections.unmodifiableSet(names);
	}

	public Collection<String> getConfigNames() {
		return Collections.unmodifiableSet(configMap.keySet());
	}

	public Collection<String> getPatternNames() {
		Set<String> patterns = Collections.emptySet();
		for (String k : patternMap.keySet()) {
			patterns.add(k);
		}
		return Collections.unmodifiableSet(patterns);
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (configLocation != null) {
			Resource resource = resourceLoader.getResource(configLocation);
			try {
				setConfig(CacheConfig.fromJSON(resource.getInputStream()));
			} catch (IOException e) {
				// try to read yaml
				try {
					setConfig(CacheConfig.fromYAML(resource.getInputStream()));
				} catch (IOException e1) {
					throw new BeanDefinitionStoreException(
							"Could not parse cache configuration at [" + configLocation + "]", e1);
				}
			}
		}
		if (regExpConfigLocation != null) {
			Resource resource = resourceLoader.getResource(regExpConfigLocation);
			Map<String, ? extends CacheConfig> confs;
			try {
				confs = CacheConfig.fromJSON(resource.getInputStream());
			} catch (IOException e) {
				// try to read yaml
				try {
					confs = CacheConfig.fromYAML(resource.getInputStream());
				} catch (IOException e1) {
					throw new BeanDefinitionStoreException(
							"Could not parse cache configuration at [" + configLocation + "]", e1);
				}
			}
			for (Map.Entry<String, ? extends CacheConfig> conf : confs.entrySet()) {
				patternMap.put(conf.getKey(), conf.getValue());
			}
		}
	}
}
