package com.github.openapi.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Copyright (c) 2017-2018 github Company LTD.
 * All rights reserved.
 *
 * @Description: @Order注解表示执行过滤顺序，值越小，越先执行
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
@Component
@ServletComponentScan
@Order(1)
@WebFilter(filterName="CharacterFilter", urlPatterns = "/*")
public class CharacterFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CharacterFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.info("~~~~~~~~~~~~~~~~~~~~~~~~CharacterFilter 执行过滤~~~~~~~~~~~~~~~~~~~~~~~");
        servletRequest.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
