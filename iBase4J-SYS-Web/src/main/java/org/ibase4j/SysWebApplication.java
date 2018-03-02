package org.ibase4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"top.ibase4j", "org.ibase4j"})
public class SysWebApplication extends SpringBootServletInitializer {
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SysWebApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SysWebApplication.class, args);
	}
}