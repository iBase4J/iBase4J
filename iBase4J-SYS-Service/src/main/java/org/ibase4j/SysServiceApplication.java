package org.ibase4j;

import org.ibase4j.core.SysServerListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SysServiceApplication {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SysServiceApplication.class);
		app.addListeners(new SysServerListener());
		app.run(args);
	}
}
