package org.ibase4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"top.ibase4j", "org.ibase4j"})
public class BizServiceApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(BizServiceApplication.class, args);
	}
}
