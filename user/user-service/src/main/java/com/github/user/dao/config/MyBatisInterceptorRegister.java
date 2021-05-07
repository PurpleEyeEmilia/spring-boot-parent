package com.github.user.dao.config;

import com.github.user.dao.interceptor.UserDaoInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Copyright (c) 2017-2018 github Company LTD.
 * All rights reserved.
 *
 * @Description:
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
@Configuration
public class MyBatisInterceptorRegister {

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public Interceptor interceptor(){
        UserDaoInterceptor userDaoInterceptor = new UserDaoInterceptor();
        Properties properties = new Properties();
        properties.setProperty("applicationName", applicationName);
        userDaoInterceptor.setProperties(properties);
        return userDaoInterceptor;
    }

}
