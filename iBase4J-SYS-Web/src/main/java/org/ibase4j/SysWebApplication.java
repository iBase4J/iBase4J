package org.ibase4j;

import org.ibase4j.core.WebServerListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class SysWebApplication extends SpringBootServletInitializer {
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SysWebApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SysWebApplication.class);
		app.addListeners(new WebServerListener());
		app.run(args);
	}
}