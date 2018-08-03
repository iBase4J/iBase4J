package org.ibase4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.alibaba.csp.sentinel.init.InitExecutor;

@SpringBootApplication(scanBasePackages = {"top.ibase4j", "org.ibase4j"})
public class BizServiceApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        InitExecutor.doInit();
        SpringApplication.run(BizServiceApplication.class, args);
    }
}
