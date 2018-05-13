package org.ibase4j.core.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import top.ibase4j.core.config.WebMvcConfig;
import top.ibase4j.core.filter.TokenFilter;
import top.ibase4j.core.interceptor.EventInterceptor;
import top.ibase4j.core.interceptor.SignInterceptor;

/**
 * @author ShenHuaJie
 * @since 2018年4月21日 下午3:30:13
 */
@Configuration
@ComponentScan("org.ibase4j.web")
public class WebConfig extends WebMvcConfig {
    @Bean
    public FilterRegistrationBean<TokenFilter> tokenFilterRegistration() {
        FilterRegistrationBean<TokenFilter> registration = new FilterRegistrationBean<TokenFilter>(new TokenFilter());
        registration.setName("tokenFilter");
        registration.addUrlPatterns("/*");
        registration.setOrder(5);
        return registration;
    }

    @Override
    @Bean
    public EventInterceptor eventInterceptor() {
        return new EventInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new SignInterceptor()).addPathPatterns("/**").excludePathPatterns("/*.ico",
            "/*/api-docs", "/swagger**", "/swagger-resources/**", "/webjars/**", "/configuration/**");
    }
}
