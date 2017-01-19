package org.ibase4j.core.support.dubbo;

import org.ibase4j.core.support.dubbo.spring.annotation.DubboReference;

import com.alibaba.dubbo.config.spring.ReferenceBean;

/**
 * @author ShenHuaJie
 *
 * @param <T>
 */
@SuppressWarnings("serial")
public class DubboReferenceConfig<T> extends ReferenceBean<T> {
	public DubboReferenceConfig() {
	}

	public DubboReferenceConfig(DubboReference reference) {
		appendAnnotation(DubboReference.class, reference);
	}
}
