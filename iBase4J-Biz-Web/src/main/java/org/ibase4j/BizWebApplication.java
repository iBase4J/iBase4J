package org.ibase4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"top.ibase4j", "org.ibase4j"})
public class BizWebApplication extends SpringBootServletInitializer {
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BizWebApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(BizWebApplication.class, args);
	}
}
