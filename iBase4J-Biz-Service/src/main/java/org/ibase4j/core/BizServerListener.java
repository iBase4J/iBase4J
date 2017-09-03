package org.ibase4j.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.core.listener.ServerListener;
import org.springframework.boot.context.event.ApplicationReadyEvent;

public class BizServerListener extends ServerListener{
    protected final Logger logger = LogManager.getLogger(this.getClass());

    public void onApplicationEvent(ApplicationReadyEvent event) {
        super.onApplicationEvent(event);
    }
}
