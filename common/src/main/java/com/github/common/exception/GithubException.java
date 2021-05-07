package com.github.common.exception;

/**
 * Copyright (c) 2017-2018 github Company LTD.
 * All rights reserved.
 *
 * @Description: 自定义业务异常
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
public class GithubException extends IllegalArgumentException {

    private String code;

    private String msg;

    private String errorMsg;

    public GithubException(ExceptionEnum exceptionEnum) {
        if(exceptionEnum != null){
            this.code = exceptionEnum.getCode();
            this.msg = exceptionEnum.getMsg();
            this.errorMsg = exceptionEnum.getErrorMsg();
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
