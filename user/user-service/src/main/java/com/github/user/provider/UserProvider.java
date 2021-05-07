package com.github.user.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.common.response.Response;
import com.github.common.response.ResponseUtils;
import com.github.common.mongodb.Page;
import com.github.user.api.UserAPI;
import com.github.user.dto.UserDto;
import com.github.user.entity.User;
import com.github.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Copyright (c) 2017-2018 github Company LTD.
 * All rights reserved.
 *
 * @Description:
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
//@Service(interfaceClass = UserAPI.class, filter = {"consumerCommonFilter"}, registry = {"registryProviderConfig"})
@Service(interfaceClass = UserAPI.class, filter = {"consumerCommonFilter"})
public class UserProvider implements UserAPI {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserProvider.class);

    @Autowired
    private UserService userService;

    @Override
    public Response<User> getUserInfo(Long id) {
        try {
            return ResponseUtils.returnObjectSuccess(userService.getUserInfo(id));
        } catch (Exception e) {
            LOGGER.error("获取用户信息失败！id = {}", id, e);
            return ResponseUtils.returnException(e);
        }
    }

    @Override
    public Response<User> insertUser(User user) {
        try {
            return ResponseUtils.returnObjectSuccess(userService.insertUser(user));
        } catch (Exception e) {
            LOGGER.error("新增用户信息失败！{}", JSON.toJSONString(user), e);
            return ResponseUtils.returnException(e);
        }
    }

    @Override
    public Response<Page<User>> getUserPage(UserDto userDto) {
        try {
            return ResponseUtils.returnObjectSuccess(userService.getUserPage(userDto));
        } catch (Exception e) {
            LOGGER.error("获取用户信息失败！", e);
            return ResponseUtils.returnException(e);
        }
    }

    @Override
    public Response<User> getUserEsInfo(String name) {
        try {
            return ResponseUtils.returnObjectSuccess(userService.getUserEsInfo(name));
        } catch (Exception e) {
            LOGGER.error("获取用户信息失败！name = {}", name, e);
            return ResponseUtils.returnException(e);
        }
    }
}
