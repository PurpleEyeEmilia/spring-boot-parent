package com.github.common.interception;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.github.common.utils.Context;

/**
 * Copyright (c) 2017-2018 github Company LTD.
 * All rights reserved.
 *
 * @Description:
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
@Activate(group = {"consumer"})
public class DubboFilter implements Filter {

    /*@Value("${spring.application.name}")
    private String applicationName;*/

    public static final String APPLICATION_NAME = "applicationName";

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        RpcContext.getContext().set(APPLICATION_NAME, Context.getProperty("spring.application.name"));
        return invoker.invoke(invocation);
    }
}
