package com.github.common.response;

import com.github.common.constants.ResponseConstant;
import com.github.common.exception.ExceptionEnum;
import com.github.common.exception.GithubException;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Copyright (c) 2017-2018 github Company LTD.
 * All rights reserved.
 *
 * @Description:
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
public class ResponseUtils {

    /**
     * void成功返回
     */
    public static <T> Response<T> returnSuccess() {
        return new Response<>(ResponseConstant.SUCCESS.getCode(), null, ResponseConstant.SUCCESS.getMsg(), "");
    }

    /**
     * 返回对象结果
     */
    public static <T> Response<T> returnObjectSuccess(T t) {
        return new Response<>(ResponseConstant.SUCCESS.getCode(), t, ResponseConstant.SUCCESS.getMsg(), "");
    }

    /**
     * 返回集合结果
     */
    public static <T> Response<T> returnListSuccess(Collection<?> collection) {
        return collection == null ?
                new Response(ResponseConstant.SUCCESS.getCode(), new ArrayList(), ResponseConstant.SUCCESS.getMsg(), "")
                : new Response(ResponseConstant.SUCCESS.getCode(), collection, ResponseConstant.SUCCESS.getMsg(), "");
    }

    /**
     * 返回异常结果
     */
    public static <T> Response<T> returnException(Exception e) {
        if (e instanceof GithubException) {
            GithubException ie = (GithubException) e;
            return new Response(false, ie.getCode(), null, ie.getMsg(), ie.getErrorMsg());
        } else if (e instanceof IllegalArgumentException) {
            IllegalArgumentException iae = (IllegalArgumentException) e;
            return new Response(false, ExceptionEnum.DATA_ILLEGAL_EXCEPTION.getCode(), new Object(), ExceptionEnum.DATA_ILLEGAL_EXCEPTION.getMsg(), iae.getMessage());
        } else {
            return new Response(false, ExceptionEnum.SYS_EXCEPTION.getCode(), new Object(), ExceptionEnum.SYS_EXCEPTION.getMsg(), ExceptionEnum.SYS_EXCEPTION.getErrorMsg());
        }
    }

    /**
     * 返回Response中的data对象，但不检查data是否为null
     *
     * @param response
     * @param <T>
     * @return
     */
    public static <T> T getDate(Response<T> response) {
        Assert.notNull(response, ExceptionEnum.RPC_RESPONSE_EXCEPTION.getErrorMsg());
        return response.getData();
    }

    /**
     * 返回Response中的data对象，检查data是否为null，若为null则抛出公共的异常消息
     *
     * @param response
     * @param <T>
     * @return
     */
    public static <T> T getDateNotNull(Response<T> response) {
        Assert.notNull(response, ExceptionEnum.RPC_RESPONSE_EXCEPTION.getErrorMsg());
        Assert.notNull(response.getData(), ExceptionEnum.RPC_RESPONSE_DATA_EXCEPTION.getErrorMsg());
        return response.getData();
    }

}
