package org.ibase4j.core.support.dubbo;

import com.alibaba.dubbo.config.spring.ServiceBean;

@SuppressWarnings("serial")
public class DubboServiceBean<T> extends ServiceBean<T> {
	public DubboServiceBean() {
	}

	public DubboServiceBean(DubboService service) {
		appendAnnotation(DubboService.class, service);
	}
}
