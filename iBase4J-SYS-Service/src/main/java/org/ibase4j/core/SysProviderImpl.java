package org.ibase4j.core;

import org.ibase4j.provider.ISysProvider;

import com.alibaba.dubbo.config.annotation.Service;
import com.weibo.api.motan.config.springsupport.annotation.MotanService;

import top.ibase4j.core.base.BaseProviderImpl;

@Service(interfaceClass = ISysProvider.class)
@MotanService(interfaceClass = ISysProvider.class)
public class SysProviderImpl extends BaseProviderImpl implements ISysProvider {

}