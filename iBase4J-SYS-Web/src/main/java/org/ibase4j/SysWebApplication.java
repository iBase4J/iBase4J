package org.ibase4j;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author ShenHuaJie
 * @since 2018年4月21日 下午3:29:03
 */
@DubboComponentScan
@SpringBootApplication(scanBasePackages = {"top.ibase4j", "org.ibase4j"})
public class SysWebApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        SpringApplication.run(SysWebApplication.class, args);
    }
}