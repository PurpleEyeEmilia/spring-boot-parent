package com.github.common.constants;

/**
 * Copyright (c) 2017-2018 github Company LTD.
 * All rights reserved.
 *
 * @Description:
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
public enum ResponseConstant {

    SUCCESS("200", "success", "success"),

    FAILURE("201", "网络开小差了，请稍候再试！", "系统异常！");

    private String code;

    private String msg;

    private String errorMsg;

    ResponseConstant(String code, String msg, String errorMsg) {
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
