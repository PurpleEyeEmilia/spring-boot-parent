package com.github.common.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.WebAppRootListener;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 * Copyright (c) 2017-2018 github Company LTD.
 * All rights reserved.
 *
 * @Description:
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class SuperDiamondConfig implements ServletContainerInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger(SuperDiamondConfig.class);

    private static final String SUPER_DIAMOND_KEY = "propertyFilePath";

    private static final String SUPER_DIAMOND_VALUE = "/superdiamond.properties";

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        servletContext.addListener(WebAppRootListener.class);
        servletContext.setInitParameter(SUPER_DIAMOND_KEY, SUPER_DIAMOND_VALUE);
        LOGGER.info("加载SuperDiamondConfig成功，配置key={}，配置value={}", SUPER_DIAMOND_KEY, SUPER_DIAMOND_VALUE);
    }

//    @Bean
//    public PropertiesConfigurationFactoryBean propertiesConfigurationFactoryBean(){
//        return new PropertiesConfigurationFactoryBean();
//    }

    /*@Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer(PropertiesConfigurationFactoryBean propertiesConfigurationFactoryBean) throws Exception {
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
        propertyPlaceholderConfigurer.setProperties(propertiesConfigurationFactoryBean.getObject());
        return propertyPlaceholderConfigurer;
    }*/

//    @Bean
//    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(PropertiesConfigurationFactoryBean propertiesConfigurationFactoryBean) throws Exception {
//        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
//        propertySourcesPlaceholderConfigurer.setProperties(propertiesConfigurationFactoryBean.getObject());
//        return propertySourcesPlaceholderConfigurer;
//    }

}
