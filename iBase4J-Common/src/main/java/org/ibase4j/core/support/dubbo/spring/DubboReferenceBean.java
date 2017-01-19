package org.ibase4j.core.support.dubbo.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ibase4j.core.support.dubbo.DubboReferenceConfig;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboReference;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ModuleConfig;
import com.alibaba.dubbo.config.MonitorConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.extension.SpringExtensionFactory;
import com.alibaba.dubbo.config.support.Parameter;

/**
 * @author ShenHuaJie
 *
 * @param <T>
 */
@SuppressWarnings({ "serial", "rawtypes" })
public class DubboReferenceBean<T> extends DubboReferenceConfig<T>
		implements FactoryBean, ApplicationContextAware, InitializingBean, DisposableBean {

	private transient ApplicationContext applicationContext;

	public DubboReferenceBean() {
		super();
	}

	public DubboReferenceBean(DubboReference reference) {
		appendAnnotation(DubboReference.class, reference);
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
		SpringExtensionFactory.addApplicationContext(applicationContext);
	}

	public Object getObject() throws Exception {
		return get();
	}

	public Class<?> getObjectType() {
		return getInterfaceClass();
	}

	@Parameter(excluded = true)
	public boolean isSingleton() {
		return true;
	}

	public void afterPropertiesSet() throws Exception {
		if (getConsumer() == null) {
			Map<String, ConsumerConfig> consumerConfigMap = applicationContext == null ? null
					: BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, ConsumerConfig.class, false,
							false);
			if (consumerConfigMap != null && consumerConfigMap.size() > 0) {
				ConsumerConfig consumerConfig = null;
				for (ConsumerConfig config : consumerConfigMap.values()) {
					if (config.isDefault() == null || config.isDefault().booleanValue()) {
						if (consumerConfig != null) {
							throw new IllegalStateException(
									"Duplicate consumer configs: " + consumerConfig + " and " + config);
						}
						consumerConfig = config;
					}
				}
				if (consumerConfig != null) {
					setConsumer(consumerConfig);
				}
			}
		}
		if (getApplication() == null && (getConsumer() == null || getConsumer().getApplication() == null)) {
			Map<String, ApplicationConfig> applicationConfigMap = applicationContext == null ? null
					: BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, ApplicationConfig.class, false,
							false);
			if (applicationConfigMap != null && applicationConfigMap.size() > 0) {
				ApplicationConfig applicationConfig = null;
				for (ApplicationConfig config : applicationConfigMap.values()) {
					if (config.isDefault() == null || config.isDefault().booleanValue()) {
						if (applicationConfig != null) {
							throw new IllegalStateException(
									"Duplicate application configs: " + applicationConfig + " and " + config);
						}
						applicationConfig = config;
					}
				}
				if (applicationConfig != null) {
					setApplication(applicationConfig);
				}
			}
		}
		if (getModule() == null && (getConsumer() == null || getConsumer().getModule() == null)) {
			Map<String, ModuleConfig> moduleConfigMap = applicationContext == null ? null
					: BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, ModuleConfig.class, false,
							false);
			if (moduleConfigMap != null && moduleConfigMap.size() > 0) {
				ModuleConfig moduleConfig = null;
				for (ModuleConfig config : moduleConfigMap.values()) {
					if (config.isDefault() == null || config.isDefault().booleanValue()) {
						if (moduleConfig != null) {
							throw new IllegalStateException(
									"Duplicate module configs: " + moduleConfig + " and " + config);
						}
						moduleConfig = config;
					}
				}
				if (moduleConfig != null) {
					setModule(moduleConfig);
				}
			}
		}
		if ((getRegistries() == null || getRegistries().size() == 0)
				&& (getConsumer() == null || getConsumer().getRegistries() == null
						|| getConsumer().getRegistries().size() == 0)
				&& (getApplication() == null || getApplication().getRegistries() == null
						|| getApplication().getRegistries().size() == 0)) {
			Map<String, RegistryConfig> registryConfigMap = applicationContext == null ? null
					: BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, RegistryConfig.class, false,
							false);
			if (registryConfigMap != null && registryConfigMap.size() > 0) {
				List<RegistryConfig> registryConfigs = new ArrayList<RegistryConfig>();
				for (RegistryConfig config : registryConfigMap.values()) {
					if (config.isDefault() == null || config.isDefault().booleanValue()) {
						registryConfigs.add(config);
					}
				}
				if (registryConfigs != null && registryConfigs.size() > 0) {
					super.setRegistries(registryConfigs);
				}
			}
		}
		if (getMonitor() == null && (getConsumer() == null || getConsumer().getMonitor() == null)
				&& (getApplication() == null || getApplication().getMonitor() == null)) {
			Map<String, MonitorConfig> monitorConfigMap = applicationContext == null ? null
					: BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, MonitorConfig.class, false,
							false);
			if (monitorConfigMap != null && monitorConfigMap.size() > 0) {
				MonitorConfig monitorConfig = null;
				for (MonitorConfig config : monitorConfigMap.values()) {
					if (config.isDefault() == null || config.isDefault().booleanValue()) {
						if (monitorConfig != null) {
							throw new IllegalStateException(
									"Duplicate monitor configs: " + monitorConfig + " and " + config);
						}
						monitorConfig = config;
					}
				}
				if (monitorConfig != null) {
					setMonitor(monitorConfig);
				}
			}
		}
		Boolean b = isInit();
		if (b == null && getConsumer() != null) {
			b = getConsumer().isInit();
		}
		if (b != null && b.booleanValue()) {
			getObject();
		}
	}
}
