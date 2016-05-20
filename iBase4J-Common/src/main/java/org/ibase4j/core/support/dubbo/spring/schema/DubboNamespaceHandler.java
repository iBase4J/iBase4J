package org.ibase4j.core.support.dubbo.spring.schema;

import org.ibase4j.core.support.dubbo.spring.AnnotationBean;

import com.alibaba.dubbo.config.spring.schema.DubboBeanDefinitionParser;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
public class DubboNamespaceHandler extends com.alibaba.dubbo.config.spring.schema.DubboNamespaceHandler {
	public void init() {
		super.init();
		registerBeanDefinitionParser("annotation", new DubboBeanDefinitionParser(AnnotationBean.class, true));
	}

}
