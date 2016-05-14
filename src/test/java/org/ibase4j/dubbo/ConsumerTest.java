package org.ibase4j.dubbo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.dubbo.config.annotation.Reference;

@ComponentScan
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:Spring-config.xml" })
public class ConsumerTest {
	@Reference
	private ProviderTest providerTest;

	@Test
	public void test() {
		providerTest.test();
	}
}
