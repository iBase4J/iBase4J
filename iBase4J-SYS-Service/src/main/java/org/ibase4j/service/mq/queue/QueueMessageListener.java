package org.ibase4j.service.mq.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;

@DubboService
public class QueueMessageListener implements MessageListener {
	private final Logger logger = LogManager.getLogger();

	public void onMessage(Message message) {
		try {
			System.out.println("QueueMessageListener接收到消息:" + ((TextMessage) message).getText());
		} catch (JMSException e) {
			logger.error(e);
		}
	}

}
