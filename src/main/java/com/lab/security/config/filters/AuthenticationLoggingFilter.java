package com.lab.security.config.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class AuthenticationLoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var httpRequest = (HttpServletRequest) request;

        var requestId = httpRequest.getHeader("Request-Id");
        log.info("Successfully authenticated request with id " + requestId);
        chain.doFilter(request,response);
    }
}
