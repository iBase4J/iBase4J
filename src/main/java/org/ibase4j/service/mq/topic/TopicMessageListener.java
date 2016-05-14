package org.ibase4j.service.mq.topic;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.dubbo.config.annotation.Service;

@Service
public class TopicMessageListener implements MessageListener {
	private final Logger logger = LogManager.getLogger();

	public void onMessage(Message message) {
		try {
			System.out.println("TopicMessageListener接收到消息:" + ((TextMessage) message).getText());
		} catch (JMSException e) {
			logger.error(e);
		}
	}
}
