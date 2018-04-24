package org.ibase4j.service.impl;

import org.ibase4j.mapper.SysFeedbackMapper;
import org.ibase4j.model.SysFeedback;
import org.ibase4j.service.ISysFeedbackService;
import org.springframework.cache.annotation.CacheConfig;

import com.alibaba.dubbo.config.annotation.Service;
import com.weibo.api.motan.config.springsupport.annotation.MotanService;

import top.ibase4j.core.base.BaseService;

/**
 * <p>
 * 反馈  服务实现类
 * </p>
 *
 * @author ShenHuaJie
 * @since 2017-03-12
 */
@CacheConfig(cacheNames = "SysFeedback")
@Service(interfaceClass = ISysFeedbackService.class)
@MotanService(interfaceClass = ISysFeedbackService.class)
public class SysFeedbackServiceImpl extends BaseService<SysFeedback, SysFeedbackMapper> implements ISysFeedbackService {

}
