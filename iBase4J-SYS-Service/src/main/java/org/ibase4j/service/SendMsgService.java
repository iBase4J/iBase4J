package org.ibase4j.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ibase4j.core.exception.BusinessException;
import org.ibase4j.core.util.CacheUtil;
import org.ibase4j.core.util.DateUtil;
import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.model.SendMsg;
import org.ibase4j.model.SysMsg;
import org.ibase4j.model.SysMsgConfig;

import com.alibaba.fastjson.JSON;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

/**
 * 发送短信服务
 * 
 * @author ShenHuaJie
 * @since 2017年3月16日 下午2:38:44
 */
@Service
public class SendMsgService {
	protected Logger logger = LogManager.getLogger(getClass());
	@Autowired
	private SysParamService paramService;
	@Autowired
	private SysMsgService msgService;
	@Autowired
	private SysMsgConfigService msgConfigService;

	public void sendMsg(Map<String, Object> paramMap) throws ApiException {
		SendMsg sendMsg = new SendMsg();
		InstanceUtil.transMap2Bean(paramMap, sendMsg);
		Map<String, Object> params = InstanceUtil.newHashMap();
		List<SysMsgConfig> configList = msgConfigService.queryList(params);
		if (configList.isEmpty()) {
			throw new BusinessException("缺少短信平台配置.");
		}
		SysMsgConfig config = configList.get(0);

		String type = "SMS_TYPE_" + sendMsg.getMsgType();
		String templateCode = paramService.getValue(type);
		if (StringUtils.isBlank(templateCode)) {
			throw new ApiException("不支持的短信类型:" + sendMsg.getMsgType());
		}
		String sender = StringUtils.defaultIfBlank(sendMsg.getSender(), config.getSenderName());

		setParams(sender, sendMsg);

		TaobaoClient client = new DefaultTaobaoClient(config.getSmsPlatUrl(), config.getSmsPlatAccount(),
				config.getSmsPlatPassword());
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setSmsType("normal");
		req.setSmsFreeSignName(config.getSenderName());
		req.setSmsParamString(sendMsg.getParams());
		req.setRecNum(sendMsg.getPhone());
		req.setSmsTemplateCode(templateCode);
		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
		logger.info(rsp.getBody());

		SysMsg record = new SysMsg();
		record.setBizId(rsp.getResult().getModel());
		record.setType(paramService.getName(type));
		record.setPhone(sendMsg.getPhone());
		record.setSendState(rsp.getResult().getSuccess() ? "1" : "0");
		record.setContent(sendMsg.getParams());
		msgService.update(record);
		if (!rsp.getResult().getSuccess()) {
			throw new ApiException(rsp.getResult().getMsg());
		}
	}

	/** 设置参数 */
	private void setParams(String sender, SendMsg sendMsg) {
		String cacheKey1, cacheKey2;
		switch (sendMsg.getMsgType()) {
		case "1":// 用户注册验证码
			cacheKey2 = "REGIN_" + sendMsg.getPhone();
			sendRandomCode(sender, sendMsg, cacheKey2);
			break;
		case "2":// 登录确认验证码
			cacheKey1 = "LOGIN_" + DateUtil.getDate() + "_" + sendMsg.getPhone();
			cacheKey2 = "LOGIN_" + sendMsg.getPhone();
			String times = StringUtils.defaultIfBlank(paramService.getValue("LOGIN_DAILY_TIMES"), "3");
			String msg = StringUtils.defaultIfBlank(paramService.getValue("LOGIN_LIMIT_MSG"), "您今天登录的次数已达到最大限制。");
			refreshSendTimes(cacheKey1, 60 * 60 * 24, Integer.parseInt(times), msg);
			sendRandomCode(sender, sendMsg, cacheKey2);
			break;
		case "3":// 修改密码验证码
			cacheKey2 = "CHGPWD_" + sendMsg.getPhone();
			sendRandomCode(sender, sendMsg, cacheKey2);
			break;
		case "4":// 身份验证验证码
			cacheKey2 = "VLDID_" + sendMsg.getPhone();
			sendRandomCode(sender, sendMsg, cacheKey2);
			break;
		case "5":// 信息变更验证码
			cacheKey2 = "CHGINFO_" + sendMsg.getPhone();
			sendRandomCode(sender, sendMsg, cacheKey2);
			break;
		case "6":// 活动确认验证码
			cacheKey2 = "AVTCMF_" + sendMsg.getPhone();
			sendRandomCode(sender, sendMsg, cacheKey2);
			break;
		default:
			break;
		}
	}

	/** 发送验证码 */
	private void sendRandomCode(String sender, SendMsg sendMsg, String cacheKey) {
		Integer random = RandomUtils.nextInt(123456, 999999);
		Map<String, String> param = InstanceUtil.newHashMap();
		param.put("code", random.toString());
		param.put("product", sender);
		if ("6".equals(sendMsg.getMsgType())) {
			param.put("", sendMsg.getParams());
		}
		sendMsg.setParams(JSON.toJSONString(param));
		CacheUtil.getCache().set(cacheKey, random.toString(), 60);
	}

	/**
	 * 发送频率检查
	 * 
	 * @param key
	 *            缓存键
	 * @param seconds
	 *            缓存有效期
	 * @param frequency
	 *            最大频率
	 * @param message
	 *            超过频率提示信息
	 */
	private void refreshSendTimes(String key, int seconds, int frequency, String message) {
		Integer times = 1;
		String timesStr = (String) CacheUtil.getCache().get(key);
		if (StringUtils.isNotBlank(timesStr)) {
			times = Integer.valueOf(timesStr) + 1;
			if (times > frequency) {
				throw new BusinessException(message);
			}
		}
		CacheUtil.getCache().set(key, times.toString(), seconds);
	}
}
