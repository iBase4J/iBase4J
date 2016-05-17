package org.ibase4j.core.support.dubbo.spring.schema;

import org.ibase4j.core.support.dubbo.spring.AnnotationBean;

import com.alibaba.dubbo.config.spring.schema.DubboBeanDefinitionParser;

public class DubboNamespaceHandler extends com.alibaba.dubbo.config.spring.schema.DubboNamespaceHandler {
	public void init() {
		super.init();
		registerBeanDefinitionParser("annotation", new DubboBeanDefinitionParser(AnnotationBean.class, true));
	}
	
	 
}
