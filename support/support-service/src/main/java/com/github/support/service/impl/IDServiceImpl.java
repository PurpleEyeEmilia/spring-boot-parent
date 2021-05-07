package com.github.support.service.impl;

import com.github.support.service.IDService;
import com.github.support.snowflake.SnowflakeGenerator;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Copyright (c) 2017-2018 github Company LTD.
 * All rights reserved.
 *
 * @Description:
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
@Service
public class IDServiceImpl implements IDService, InitializingBean {

    /**
     * 数据中心标识0~31
     */
    @Value("${dataCenterId}")
    private long dataCenterId;

    /**
     * 机器标识0~31
     */
    @Value("${machineId}")
    private long machineId;

    /**
     * 雪花ID生成器
     */
    private SnowflakeGenerator snowflakeGenerator;


    @Override
    public Long getSnowflakeID() {
        return snowflakeGenerator.nextId();
    }

    @Override
    public String parseUID(long uid) {

        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        snowflakeGenerator = new SnowflakeGenerator(dataCenterId, machineId);
    }

}
