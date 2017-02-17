package org.ibase4j.core;

import org.ibase4j.core.base.BaseProviderImpl;
import org.ibase4j.provider.IBizProvider;

import com.alibaba.dubbo.config.annotation.Service;

@Service(interfaceClass = IBizProvider.class)
public class BizProviderImpl extends BaseProviderImpl implements IBizProvider {

}