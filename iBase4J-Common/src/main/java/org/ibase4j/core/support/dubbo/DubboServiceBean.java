package org.ibase4j.core.support.dubbo;

import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;

import com.alibaba.dubbo.config.spring.ServiceBean;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@SuppressWarnings("serial")
public  final class DubboServiceBean<T> extends ServiceBean<T> {
	public DubboServiceBean() {
	}

	public DubboServiceBean(DubboService service) {
		appendAnnotation(DubboService.class, service);
	}
}
