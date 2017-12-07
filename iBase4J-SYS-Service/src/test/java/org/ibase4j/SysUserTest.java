package org.ibase4j;

import java.util.Map;

import org.ibase4j.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;

import top.ibase4j.core.util.InstanceUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserTest {
    @Autowired
	SysUserService sysUserService;

    @Test
	public void testAuery() {
		Map<String, Object> params = InstanceUtil.newHashMap();
		System.out.println(JSON.toJSONString(sysUserService.query(params)));
	}
}
