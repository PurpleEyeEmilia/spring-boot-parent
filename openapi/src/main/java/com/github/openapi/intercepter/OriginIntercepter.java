package com.github.openapi.intercepter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Copyright (c) 2017-2018 github Company LTD.
 * All rights reserved.
 *
 * @Description:跨域拦截器
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
public class OriginIntercepter extends HandlerInterceptorAdapter {

    public static final Logger LOGGER = LoggerFactory.getLogger(OriginIntercepter.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Method","POST,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers","Origin, Content-Encoding ,Accept, X-Requested-With, "
                + "Content-Type, X-Forwarded-For, Proxy-Client-IP, WL-Proxy-Client-IP, HTTP_CLIENT_IP, "
                + "HTTP_X_FORWARDED_FOR, sign, token, appVersion, devType, devName, devId, ip, net, userId, appId, appSecret, clientVersion, ditchCode, "
                + "appVersion, appId, devId, language, ditchCode"
        );
        response.setContentType("text/json;charset=UTF-8");

        LOGGER.info("OPTIONS Record, Request URI Is {}", request.getRequestURI());
        return RequestMethod.OPTIONS.name().equalsIgnoreCase(request.getMethod()) ? false : true;
    }
}
