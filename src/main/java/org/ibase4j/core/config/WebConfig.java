package org.ibase4j.core.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import top.ibase4j.core.config.WebMvcConfig;
import top.ibase4j.core.filter.TokenFilter;
import top.ibase4j.core.interceptor.EventInterceptor;

@Configuration
@ComponentScan({"org.iteachs.web"})
public class WebConfig extends WebMvcConfig {
    @Bean
    public FilterRegistrationBean<TokenFilter> tokenFilterRegistration() {
        FilterRegistrationBean<TokenFilter> registration = new FilterRegistrationBean<TokenFilter>(new TokenFilter());
        registration.setName("tokenFilter");
        registration.addUrlPatterns("/app/*");
        registration.setOrder(4);
        return registration;
    }


    @Bean
    public EventInterceptor eventInterceptor() {
        return new EventInterceptor();
    }
}
