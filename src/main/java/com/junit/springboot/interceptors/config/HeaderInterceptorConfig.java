package com.junit.springboot.interceptors.config;

import com.junit.springboot.interceptors.ServletRequestHeaderHolder;
import com.junit.springboot.interceptors.ServletRequestHeaderInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class HeaderInterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(servletRequestHeaderInterceptor());
    }

    @Bean
    public ServletRequestHeaderInterceptor servletRequestHeaderInterceptor() {
        return new ServletRequestHeaderInterceptor(servletRequestHeaderHolder());
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public ServletRequestHeaderHolder servletRequestHeaderHolder() {
        return new ServletRequestHeaderHolder();
    }

}