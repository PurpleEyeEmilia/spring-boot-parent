package com.github.openapi.exception;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.common.response.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Copyright (c) 2017-2018 github Company LTD.
 * All rights reserved.
 *
 * @Description:异常处理器
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
@Component
public class ExceptionHandler implements HandlerExceptionResolver {

    public static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        //设置响应对象
        try {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.getWriter().write(JSON.toJSONString(ResponseUtils.returnException(e), SerializerFeature.WriteMapNullValue));
            LOGGER.error("[ExceptionHandler]", e);
        } catch (Exception e1) {
            LOGGER.error("处理异常失败！", e);
        }
        return new ModelAndView();
    }
}
