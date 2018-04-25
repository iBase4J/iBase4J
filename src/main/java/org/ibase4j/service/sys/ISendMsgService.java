package org.ibase4j.service.sys;

import org.ibase4j.model.sys.SendMsg;

/**
 * @author ShenHuaJie
 * @since 2018年4月24日 上午10:59:45
 */
public interface ISendMsgService {
    /**
     * 发送短信验证码
     * @param sendMsg
     */
    public void sendMsg(SendMsg sendMsg);

    /**
     * 发送语音验证码
     * @param sendMsg
     */
    public void sendTts(SendMsg sendMsg);
}
