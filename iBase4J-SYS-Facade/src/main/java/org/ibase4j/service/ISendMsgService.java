package org.ibase4j.service;

import org.ibase4j.model.SendMsg;

/**
 * @author ShenHuaJie
 * @since 2018年2月26日 下午7:27:52
 */
public interface ISendMsgService {
	public void sendMsg(SendMsg sendMsg);

	public void sendTts(SendMsg sendMsg);
}
