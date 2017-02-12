package org.ibase4j.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author ShenHuaJie
 * @version 2016年6月21日 上午9:50:58
 */
@Configuration
@EnableWebMvc
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket platformApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("full-platform").apiInfo(apiInfo())
				.forCodeGeneration(true);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("iBase4J-API").description("©2016 Copyright. Powered By iBase4J.")
				// .termsOfServiceUrl("")
				.contact(new Contact("iBase4J", "", "iBase4J@163.com")).license("Apache License Version 2.0")
				.licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE").version("2.0").build();
	}

}