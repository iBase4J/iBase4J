package org.ibase4j.core;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.stereotype.Component;

import top.ibase4j.core.Constants;
import top.ibase4j.core.listener.ApplicationReadyListener;
import top.ibase4j.core.util.CacheUtil;

@Component
public class ServerListener extends ApplicationReadyListener {
	public void onApplicationEvent(ApplicationReadyEvent event) {
		CacheUtil.getCache().delAll(Constants.MYBATIS_CACHE + "*");
		super.onApplicationEvent(event);
	}
}