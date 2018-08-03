package org.ibase4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.alibaba.csp.sentinel.init.InitExecutor;

/**
 * @author ShenHuaJie
 * @since 2018年4月21日 下午3:29:03
 */
@SpringBootApplication(scanBasePackages = {"top.ibase4j", "org.ibase4j"})
public class SysWebApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        InitExecutor.doInit();
        SpringApplication.run(SysWebApplication.class, args);
    }
}