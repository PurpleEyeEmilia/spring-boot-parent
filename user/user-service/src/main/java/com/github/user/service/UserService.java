package com.github.user.service;

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
public interface UserService {

    User getUserInfo(Long Id);

    User insertUser(User user);

    void handleMessage(String msg);

    Page<User> getUserPage(UserDto userDto);

    User getUserEsInfo(String name);
}
