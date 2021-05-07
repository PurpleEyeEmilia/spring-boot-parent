package com.github.support.api;


import com.github.common.response.Response;

/**
 * Copyright (c) 2017-2018 github Company LTD.
 * All rights reserved.
 *
 * @Description:
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
public interface IDApi {

    /**
     * 获取雪花算法ID
     *
     * @return
     */
    Response<Long> getSnowflakeID();

}
