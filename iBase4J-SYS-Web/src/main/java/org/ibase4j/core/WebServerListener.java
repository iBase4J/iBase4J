package org.ibase4j.core;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.stereotype.Component;

import top.ibase4j.core.listener.ServerListener;

@Component
public class WebServerListener extends ServerListener {

	public void onApplicationEvent(ApplicationReadyEvent event) {
		super.onApplicationEvent(event);
	}
}