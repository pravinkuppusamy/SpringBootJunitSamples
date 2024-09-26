package com.junit.springboot.entity.audit;

import com.junit.springboot.interceptors.ServletRequestHeaderHolder;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    private final ServletRequestHeaderHolder servletRequestHeaderHolder;

    public AuditorAwareImpl(ServletRequestHeaderHolder servletRequestHeaderHolder) {
        this.servletRequestHeaderHolder = servletRequestHeaderHolder;
    }

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(servletRequestHeaderHolder.getRequesterName());
    }
}