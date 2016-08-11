package org.ibase4j;

import org.ibase4j.model.generator.SysUser;
import org.ibase4j.provider.sys.SysUserProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.dubbo.config.annotation.Reference;

@ComponentScan
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:Spring-config.xml" })
public class DubboTest {
	@Reference
	private SysUserProvider sysUserProvider;

	@Test
	public void test() { // 依赖sys-service
		SysUser sysUser = sysUserProvider.queryById("1");
		Assert.assertEquals("admin", sysUser.getAccount());
	}
}
