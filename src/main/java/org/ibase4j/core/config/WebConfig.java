package org.ibase4j.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import top.ibase4j.core.config.WebMvcConfig;
import top.ibase4j.core.interceptor.EventInterceptor;
import top.ibase4j.core.interceptor.TokenInterceptor;

@Configuration
@ComponentScan({"org.ibase4j.web"})
public class WebConfig extends WebMvcConfig {
    @Override
    @Bean
    public EventInterceptor eventInterceptor() {
        return new EventInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/**").excludePathPatterns("/*.ico",
            "/*/api-docs", "/swagger**", "/swagger-resources/**", "/webjars/**", "/configuration/**");
    }
}
