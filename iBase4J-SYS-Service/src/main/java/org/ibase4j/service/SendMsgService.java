package org.ibase4j.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.model.SendMsg;
import org.ibase4j.model.SysMsg;
import org.ibase4j.model.SysMsgConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.dyvmsapi.model.v20170525.SingleCallByTtsRequest;
import com.aliyuncs.dyvmsapi.model.v20170525.SingleCallByTtsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.baomidou.mybatisplus.toolkit.IdWorker;

import top.ibase4j.core.Constants.MSGCHKTYPE;
import top.ibase4j.core.util.CacheUtil;
import top.ibase4j.core.util.DateUtil;
import top.ibase4j.core.util.InstanceUtil;

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

    public void sendMsg(SendMsg sendMsg) throws ClientException {
        Map<String, Object> params = InstanceUtil.newHashMap();
        List<SysMsgConfig> configList = msgConfigService.queryList(params);
        if (configList.isEmpty()) {
            throw new RuntimeException("缺少短信平台配置.");
        }
        SysMsgConfig config = configList.get(0);

        String type = "SMS_TYPE_" + sendMsg.getBizType();
        String templateCode = paramService.getValue(type);
        if (StringUtils.isBlank(templateCode)) {
            throw new RuntimeException("不支持的短信类型:" + sendMsg.getBizType());
        }
        String sender = StringUtils.defaultIfBlank(sendMsg.getSender(), config.getSenderName());

        setParams(sender, sendMsg);

        // 设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        // 初始化ascClient,暂时不支持多region
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", config.getSmsPlatAccount(),
            config.getSmsPlatPassword());
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", config.getSmsPlatUrl());
        IAcsClient acsClient = new DefaultAcsClient(profile);

        // 组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        // 使用post提交
        request.setMethod(MethodType.POST);
        // 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        request.setPhoneNumbers(sendMsg.getPhone());
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName(config.getSenderName());
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        // 友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam(sendMsg.getParams());
        // 可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        // request.setSmsUpExtendCode("90997");
        // 请求失败这里会抛ClientException异常
        SendSmsResponse response = acsClient.getAcsResponse(request);
        logger.info(JSON.toJSONString(response));
        SysMsg record = new SysMsg();
        if (response.getCode() != null) {
            record.setBizId(response.getRequestId());
            if (response.getCode().equals("OK")) {
                // 请求成功
                record.setSendState("1");
            } else {
                record.setSendState("0");
                response.setMessage(paramService.getValue(response.getCode(), response.getMessage()));
            }
        } else {
            record.setBizId(IdWorker.get32UUID());
            record.setSendState("0");
        }
        record.setType(paramService.getName(type));
        record.setPhone(sendMsg.getPhone());
        record.setContent(sendMsg.getParams());
        msgService.update(record);

        if ("0".equals(record.getSendState())) {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void sendTts(SendMsg sendMsg) throws ClientException {
        Map<String, Object> params = InstanceUtil.newHashMap();
        List<SysMsgConfig> configList = msgConfigService.queryList(params);
        if (configList.isEmpty()) {
            throw new RuntimeException("缺少短信平台配置.");
        }
        SysMsgConfig config = configList.get(0);

        String type = "TTS_TYPE_" + sendMsg.getBizType();
        String templateCode = paramService.getValue(type);
        if (StringUtils.isBlank(templateCode)) {
            throw new RuntimeException("不支持的短信类型:" + sendMsg.getBizType());
        }
        String sender = StringUtils.defaultIfBlank(sendMsg.getSender(), config.getSenderName());

        setParams(sender, sendMsg);

        // 设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        // 初始化ascClient,暂时不支持多region
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", config.getSmsPlatAccount(),
            config.getSmsPlatPassword());
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dyvmsapi", "dyvmsapi.aliyuncs.com");
        IAcsClient acsClient = new DefaultAcsClient(profile);

        // 组装请求对象
        SingleCallByTtsRequest request = new SingleCallByTtsRequest();
        // 必填-被叫显号,可在语音控制台中找到所购买的显号
        request.setCalledShowNumber(paramService.getValue("TTS_CALL_NUMBER"));
        // 必填-被叫号码
        request.setCalledNumber(sendMsg.getPhone());
        // 必填-Tts模板ID
        request.setTtsCode(templateCode);
        // 可选-当模板中存在变量时需要设置此值
        request.setTtsParam(sendMsg.getParams());
        // 请求失败这里会抛ClientException异常
        SingleCallByTtsResponse response = acsClient.getAcsResponse(request);
        logger.info(JSON.toJSONString(response));
        SysMsg record = new SysMsg();
        if (response.getCode() != null) {
            record.setBizId(response.getRequestId());
            if (response.getCode().equals("OK")) {
                // 请求成功
                record.setSendState("1");
            } else {
                record.setSendState("0");
            }
        } else {
            record.setBizId(IdWorker.get32UUID());
            record.setSendState("0");
        }
        record.setType(paramService.getName(type));
        record.setPhone(sendMsg.getPhone());
        record.setContent(sendMsg.getParams());
        msgService.update(record);

        if ("0".equals(record.getSendState())) {
            throw new RuntimeException(response.getMessage());
        }
    }

    /** 设置参数 */
    private void setParams(String sender, SendMsg sendMsg) {
        String cacheKey1, cacheKey2;
        switch (sendMsg.getBizType()) {
        case "1":// 用户注册验证码
            cacheKey2 = MSGCHKTYPE.REGISTER + sendMsg.getPhone();
            sendRandomCode(sender, sendMsg, cacheKey2);
            break;
        case "2":// 登录确认验证码
            cacheKey1 = MSGCHKTYPE.LOGIN + DateUtil.getDate() + "_" + sendMsg.getPhone();
            cacheKey2 = MSGCHKTYPE.LOGIN + sendMsg.getPhone();
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
        if ("6".equals(sendMsg.getBizType())) {
            param.put("", sendMsg.getParams());
        }
        sendMsg.setParams(JSON.toJSONString(param));
        String seconds = paramService.getValue("AUTH-CODE-EXPIRATION-SMS" + sendMsg.getBizType(), "120");
        CacheUtil.getCache().set(cacheKey, random.toString(), Integer.valueOf(seconds));
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
        if (CacheUtil.getLock(key + "-LOCK")) {
            try {
                Integer times = 1;
                String timesStr = (String)CacheUtil.getCache().get(key);
                if (StringUtils.isNotBlank(timesStr)) {
                    times = Integer.valueOf(timesStr) + 1;
                    if (times > frequency) {
                        throw new RuntimeException(message);
                    }
                }
                CacheUtil.getCache().set(key, times.toString(), seconds);
            } finally {
                CacheUtil.unlock(key + "-LOCK");
            }
        } else {
            refreshSendTimes(key, seconds, frequency, message);
        }
    }
}
