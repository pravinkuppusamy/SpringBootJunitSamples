package com.junit.springboot.interceptors;

import com.junit.springboot.utils.CommonConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class ServletRequestHeaderInterceptor implements HandlerInterceptor {

    private final ServletRequestHeaderHolder servletRequestHeaderHolder;

    public ServletRequestHeaderInterceptor(ServletRequestHeaderHolder servletRequestHeaderHolder) {
        this.servletRequestHeaderHolder = servletRequestHeaderHolder;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        servletRequestHeaderHolder.setRequesterName(request.getHeader(CommonConstant.X_REQUESTER_NAME));
        servletRequestHeaderHolder.setTenantId(request.getHeader(CommonConstant.X_TENANT_ID));
        return true;
    }


}