package com.github.common.exception;

/**
 * Copyright (c) 2017-2018 github Company LTD.
 * All rights reserved.
 *
 * @Description:
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
public enum ExceptionEnum {

    SYS_EXCEPTION("1000", "网络开小差了，请稍候再试！", "系统异常！"),
    DATA_ILLEGAL_EXCEPTION("1001", "数据验证失败！", "数据验证失败！"),

    RPC_RESPONSE_EXCEPTION("2002", "网络开小差了，请稍候再试！", "RpcResponse对象为空！"),
    RPC_RESPONSE_DATA_EXCEPTION("2003", "网络开小差了，请稍候再试！", "RpcResponse对象返回Data为空！");

    private String code;

    private String msg;

    private String errorMsg;

    ExceptionEnum(String code, String msg, String errorMsg) {
        this.code = code;
        this.msg = msg;
        this.errorMsg = errorMsg;
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
}
