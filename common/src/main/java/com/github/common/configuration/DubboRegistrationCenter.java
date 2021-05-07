package com.github.common.configuration;

import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright (c) 2017-2018 github Company LTD.
 * All rights reserved.
 *
 * @Description: Dubbo多注册中心的配置
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
@Configuration
public class DubboRegistrationCenter {

    @Bean(name = "registryConsumerConfig")
    public RegistryConfig registryConsumerConfig(){
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("10.4.7.51:2181");
        registryConfig.setProtocol("zookeeper");
        return registryConfig;
    }

    @Bean(name = "registryProviderConfig")
    public RegistryConfig registryProviderConfig(){
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("127.0.0.1:2181");
        registryConfig.setProtocol("zookeeper");
        return registryConfig;
    }

    @Bean
    public ProtocolConfig protocolConfig(){
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setPort(20899);
        return protocolConfig;
    }
}
