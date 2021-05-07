package com.github.user.api;

import com.github.common.response.Response;
import com.github.common.mongodb.Page;
import com.github.user.entity.User;
import com.github.user.dto.UserDto;

/**
 * Copyright (c) 2017-2018 github Company LTD.
 * All rights reserved.
 *
 * @Description:
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
public interface UserAPI {

    /**
     * 获取用户信息
     * @param id
     * @return
     */
    Response<User> getUserInfo(Long id);

    /**
     * 插入用户信息
     * @param user
     * @return
     */
    Response<User> insertUser(User user);

    /**
     * 分页查询用户
     *
     * @param userDto
     * @return
     */
    Response<Page<User>> getUserPage(UserDto userDto);

    /**
     * 查询es中的数据
     *
     * @param name
     * @return
     */
    Response<User> getUserEsInfo(String name);
}
