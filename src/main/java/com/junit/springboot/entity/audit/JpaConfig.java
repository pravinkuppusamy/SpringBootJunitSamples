package com.junit.springboot.entity.audit;


import com.junit.springboot.interceptors.ServletRequestHeaderHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
class JpaConfig {

    private final ServletRequestHeaderHolder servletRequestHeaderHolder;

    @Autowired
    JpaConfig(ServletRequestHeaderHolder servletRequestHeaderHolder) {
        this.servletRequestHeaderHolder = servletRequestHeaderHolder;
    }

    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl(servletRequestHeaderHolder);
    }
}