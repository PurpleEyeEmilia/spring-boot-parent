package com.github.user.dao;


import com.github.user.dto.UserDto;
import com.github.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Copyright (c) 2017-2018 github Company LTD.
 * All rights reserved.
 *
 * @Description:
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
@Mapper
public interface UserDao {

    User getUserInfo(Long id);

    Integer insertUser(User user);

    List<User> getUserPage(UserDto userDto);
}
