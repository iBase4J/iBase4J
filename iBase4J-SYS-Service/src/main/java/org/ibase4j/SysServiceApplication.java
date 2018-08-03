package org.ibase4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.alibaba.csp.sentinel.init.InitExecutor;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;

@DubboComponentScan(basePackages = "org.ibase4j")
@SpringBootApplication(scanBasePackages = {"top.ibase4j", "org.ibase4j"})
public class SysServiceApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        InitExecutor.doInit();
        SpringApplication.run(SysServiceApplication.class, args);
    }
}
