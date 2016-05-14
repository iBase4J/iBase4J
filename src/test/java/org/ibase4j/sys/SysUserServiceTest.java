/**
 * 
 */
package org.ibase4j.sys;

import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.HashMap;

import org.apache.logging.log4j.Logger;
import org.ibase4j.mybatis.generator.model.SysUser;
import org.ibase4j.service.sys.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;

/**
 * @author ShenHuaJie
 * @version 2016年4月9日 下午12:52:09
 */
@ComponentScan
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:Spring-config.xml")
public class SysUserServiceTest {
	private Logger logger = getLogger();
	@Autowired
	SysUserService sysUserService;

	@Test
	public void testEmptyCollection() {
		PageInfo<SysUser> page = sysUserService.query(new HashMap<String, Object>());
		logger.info(JSON.toJSONString(page));
	}
}
