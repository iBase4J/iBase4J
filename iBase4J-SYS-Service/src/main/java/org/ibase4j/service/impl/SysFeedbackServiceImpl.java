package org.ibase4j.service.impl;

import org.ibase4j.mapper.SysFeedbackMapper;
import org.ibase4j.model.SysFeedback;
import org.ibase4j.service.SysFeedbackService;
import org.springframework.cache.annotation.CacheConfig;

import org.apache.dubbo.config.annotation.Service;

import top.ibase4j.core.base.BaseServiceImpl;

/**
 * <p>
 * 反馈  服务实现类
 * </p>
 *
 * @author ShenHuaJie
 * @since 2017-03-12
 */
@CacheConfig(cacheNames = "SysFeedback")
@Service(interfaceClass = SysFeedbackService.class)
public class SysFeedbackServiceImpl extends BaseServiceImpl<SysFeedback, SysFeedbackMapper>
implements SysFeedbackService {

}
