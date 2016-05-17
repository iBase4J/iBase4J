package org.ibase4j.core.support.dubbo.spring;

import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;

import com.alibaba.dubbo.config.spring.ServiceBean;

@SuppressWarnings("serial")
public class DubboServiceBean<T> extends ServiceBean<T> {
	public DubboServiceBean() {
		super();
	}

	public DubboServiceBean(DubboService service) {
		appendAnnotation(DubboService.class, service);
	}
}
