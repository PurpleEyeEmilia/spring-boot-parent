package com.github.support.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.common.response.Response;
import com.github.common.response.ResponseUtils;
import com.github.support.api.IDApi;
import com.github.support.service.IDService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Copyright (c) 2017-2018 github Company LTD.
 * All rights reserved.
 *
 * @Description:
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
@Service(interfaceClass = IDApi.class)
public class IDProvider implements IDApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(IDProvider.class);

    @Autowired
    private IDService idService;

    @Override
    public Response<Long> getSnowflakeID() {
        try {
            return ResponseUtils.returnObjectSuccess(idService.getSnowflakeID());
        } catch (Exception e) {
            LOGGER.error("Generate SnowflakeID Exception，", e);
            return ResponseUtils.returnException(e);
        }
    }
}
