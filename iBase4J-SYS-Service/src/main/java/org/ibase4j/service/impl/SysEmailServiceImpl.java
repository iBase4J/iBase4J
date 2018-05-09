package org.ibase4j.service.impl;

import java.util.List;

import org.ibase4j.mapper.SysEmailMapper;
import org.ibase4j.model.SysEmail;
import org.ibase4j.model.SysEmailConfig;
import org.ibase4j.service.ISysEmailConfigService;
import org.ibase4j.service.ISysEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.weibo.api.motan.config.springsupport.annotation.MotanService;

import top.ibase4j.core.base.BaseService;
import top.ibase4j.core.support.email.Email;
import top.ibase4j.core.util.DataUtil;
import top.ibase4j.core.util.EmailUtil;
import top.ibase4j.core.util.InstanceUtil;

/**
 * @author ShenHuaJie
 *
 */
@CacheConfig(cacheNames = "sysEmail")
@Service(interfaceClass = ISysEmailService.class)
@MotanService(interfaceClass = ISysEmailService.class)
public class SysEmailServiceImpl extends BaseService<SysEmail, SysEmailMapper> implements ISysEmailService {
    @Autowired
    private ISysEmailConfigService emailConfigService;

    @Transactional
    public void sendEmail(SysEmail record) {
        Email email = new Email(record.getSender(), record.getEmailTitle(), record.getEmailContent());
        List<SysEmailConfig> emailConfigs = emailConfigService.queryList(InstanceUtil.newHashMap());
        if (DataUtil.isEmpty(emailConfigs)) {
            logger.warn("缺少邮件配置, 使用配置文件默认配置");
        } else {
            SysEmailConfig emailConfig = emailConfigs.get(0);
            email.setHost(emailConfig.getSmtpHost());
            email.setPort(emailConfig.getSmtpPort());
            email.setName(emailConfig.getSenderName());
            email.setFrom(emailConfig.getSenderName() + "," + emailConfig.getSenderAccount());
            email.setPassword(emailConfig.getSenderPassword());
            email.setUserKey(emailConfig.getSenderPasswordAuth());
            if (emailConfig.getIsSSL() != null) {
                email.setSSL(emailConfig.getIsSSL());
            }
        }
        EmailUtil.sendEmail(email);
        update(record);
    }
}
