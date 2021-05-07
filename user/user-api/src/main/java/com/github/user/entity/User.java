package com.github.user.entity;

import com.github.common.entity.BaseEntity;

/**
 * Copyright (c) 2017-2018 github Company LTD.
 * All rights reserved.
 *
 * @Description:
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
public class User extends BaseEntity {

    /**
     * 雪花id
     */
    private Long kid;

    /**
     * 姓名
     */
    private String name;

    /**
     * 电话
     */
    private String phone;

    public Long getKid() {
        return kid;
    }

    public void setKid(Long kid) {
        this.kid = kid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
