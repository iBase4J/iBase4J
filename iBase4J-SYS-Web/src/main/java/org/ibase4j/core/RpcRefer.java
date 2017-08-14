package org.ibase4j.core;

import org.ibase4j.provider.ISysProvider;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.weibo.api.motan.config.springsupport.annotation.MotanReferer;

@Component
public class RpcRefer {
	@Reference(check = false)
	@MotanReferer(check = false)
	ISysProvider sysProvider;
}
