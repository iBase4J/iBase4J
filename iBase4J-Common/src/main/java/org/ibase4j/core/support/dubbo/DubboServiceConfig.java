package org.ibase4j.core.support.dubbo;

import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;

import com.alibaba.dubbo.config.spring.ServiceBean;

/**
 * @author ShenHuaJie
 *
 * @param <T>
 */
@SuppressWarnings("serial")
public class DubboServiceConfig<T> extends ServiceBean<T> {
	public DubboServiceConfig() {
	}

	public DubboServiceConfig(DubboService service) {
		appendAnnotation(DubboService.class, service);
	}
}
