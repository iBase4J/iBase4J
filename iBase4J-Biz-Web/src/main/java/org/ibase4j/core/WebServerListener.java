package org.ibase4j.core;

import org.ibase4j.core.listener.ServerListener;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.stereotype.Component;

@Component
public class WebServerListener extends ServerListener {

	public void onApplicationEvent(ApplicationReadyEvent event) {
		super.onApplicationEvent(event);
	}
}