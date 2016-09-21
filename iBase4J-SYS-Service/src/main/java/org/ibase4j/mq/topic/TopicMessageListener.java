package org.ibase4j.mq.topic;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class TopicMessageListener implements MessageListener {
	private final Logger logger = LogManager.getLogger();

	public void onMessage(Message message) {
		try {
			System.out.println("TopicMessageListener接收到消息:" + ((ObjectMessage) message).getObject());
		} catch (JMSException e) {
			logger.error(e);
		}
	}
}
