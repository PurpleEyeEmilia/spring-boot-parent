package com.github.common.response;

import com.github.common.constants.ResponseConstant;

import java.io.Serializable;
import java.util.Objects;

/**
 * Copyright (c) 2017-2018 github Company LTD.
 * All rights reserved.
 *
 * @Description:
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
public class Response<T> implements Serializable {

    /**
     * RPC接口调用是否成功状态
     */
    private Boolean status = true;

    /**
     * RPC接口返回的状态码
     */
    private String code = ResponseConstant.SUCCESS.getCode();

    /**
     * RPC接口返回的接口调用信息
     */
    private String msg = ResponseConstant.SUCCESS.getMsg();

    /**
     * RPC接口返回的接口调用异常信息
     */
    private String errorMsg;

    /**
     * RPC接口返回的数据对象
     */
    private T data;

    public Response() {
    }

    public Response(String code, T data, String msg, String errorMsg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.errorMsg = errorMsg;
    }

    public Response(Boolean status, String code, T data, String msg, String errorMsg) {
        this.status = status;
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.errorMsg = errorMsg;
    }

    /**
     * 调用结果状态
     * @return
     */
    public Boolean success(){
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "response{" +
                "status=" + status +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response<?> response = (Response<?>) o;
        return Objects.equals(status, response.status) &&
                Objects.equals(code, response.code) &&
                Objects.equals(msg, response.msg) &&
                Objects.equals(errorMsg, response.errorMsg) &&
                Objects.equals(data, response.data);
    }

    @Override
    public int hashCode() {

        return Objects.hash(status, code, msg, errorMsg, data);
    }
}
