package org.ibase4j.core;

import org.ibase4j.provider.IBizProvider;

import com.alibaba.dubbo.config.annotation.Service;
import com.weibo.api.motan.config.springsupport.annotation.MotanService;

import top.ibase4j.core.base.BaseProviderImpl;

@Service(interfaceClass = IBizProvider.class)
@MotanService(interfaceClass = IBizProvider.class)
public class BizProviderImpl extends BaseProviderImpl implements IBizProvider {

}