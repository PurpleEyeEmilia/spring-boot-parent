package com.github.openapi.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.common.response.Response;
import com.github.common.mongodb.Page;
import com.github.user.api.UserAPI;
import com.github.user.dto.UserDto;
import com.github.user.entity.User;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright (c) 2017-2018 github Company LTD.
 * All rights reserved.
 *
 * @Description:
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
@RestController
@RequestMapping("user")
public class UserController {

//    @Reference(check = false, timeout = 30000, retries = 1, registry = {"registryConsumerConfig"})
    @Reference(check = false, timeout = 30000, retries = 1)
    private UserAPI userAPI;

    /**
     * 查询用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "info/{id}", method = RequestMethod.GET)
    public Response<User> getUserInfo(@PathVariable Long id) {
        Assert.notNull(id, "用户id不能为空！");
        return userAPI.getUserInfo(id);
    }

    /**
     * 插入用户
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "info", method = RequestMethod.POST)
    public Response<User> insertUser(@RequestBody User user) {
        Assert.notNull(user, "缺少必要参数！");
        return userAPI.insertUser(user);
    }

    /**
     * 分页查询用户
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping("info/{pageNo}/{pageSize}")
    public Response<Page<User>> getUserPage(@PathVariable Integer pageNo, @PathVariable Integer pageSize) {
        Assert.notNull(pageNo, "当前页不能为空！");
        Assert.notNull(pageSize, "页大小不能为空！");
        UserDto userDto = new UserDto();
        userDto.setPageNo(pageNo == 0 ? 1 : pageNo);
        userDto.setPageSize(pageSize == 0 ? 10 : pageSize);
        return userAPI.getUserPage(userDto);
    }

    /**
     * 根据名称搜索es，返回数据
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "es", method = RequestMethod.GET)
    public Response<User> getUserEsInfo(String name) {
        Assert.hasText(name, "查询name不能为空！");
        return userAPI.getUserEsInfo(name);
    }

}
