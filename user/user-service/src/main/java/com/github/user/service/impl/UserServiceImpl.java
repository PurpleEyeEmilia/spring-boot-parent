package com.github.user.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.common.cache.RedisTemplateBuilder;
import com.github.common.interception.DubboFilter;
import com.github.common.mongodb.Page;
import com.github.common.utils.PageUtils;
import com.github.support.api.IDApi;
import com.github.user.mq.MessageSender;
import com.github.user.dao.UserDao;
import com.github.user.dto.UserDto;
import com.github.user.elasticsearch.UserEsEntity;
import com.github.user.elasticsearch.UserEsRepository;
import com.github.user.entity.User;
import com.github.user.mongo.UserMongo;
import com.github.user.service.UserService;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Copyright (c) 2017-2018 github Company LTD.
 * All rights reserved.
 *
 * @Description:
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisTemplateBuilder redisTemplateBuilder;

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private UserMongo userMongo;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private UserEsRepository userEsRepository;

    @Reference(check = false, timeout = 30000)
    private IDApi idApi;

    @Value("${redis.host}")
    private String redisHost;

    @Value("${redis.port}")
    private String redisPort;

    private static final String USER_ID = "user:id:";

    @Override
    public User getUserInfo(Long id) {
        String applicationName = (String) RpcContext.getContext().get(DubboFilter.APPLICATION_NAME);

        System.out.println("微服务名：" + applicationName);

        RedisTemplate<String, User> redisTemplate = redisTemplateBuilder.build(User.class);
        User user = redisTemplate.opsForValue().get(USER_ID + id);
        if (user != null) {
            return user;
        }
        User userInfo = userDao.getUserInfo(id);
        Assert.notNull(userInfo, "无法根据当期id查询到用户信息");
        redisTemplate.opsForValue().set(USER_ID + id, userInfo);

        System.out.println(redisHost + "===" + redisPort);

        return userInfo;
    }

    @Override
    @Transactional
    public User insertUser(User user) {
        Integer count = userDao.insertUser(user);
        if (count > 0) {
            RedisTemplate<String, User> redisTemplate = redisTemplateBuilder.build(User.class);
            redisTemplate.opsForValue().set(USER_ID + user.getId(), user);
        }
        messageSender.sendMessage(JSON.toJSONString(user));

        //同步es
        if (elasticsearchTemplate.indexExists(UserEsEntity.class)) {
            elasticsearchTemplate.createIndex(UserEsEntity.class);
            UserEsEntity userEsEntity = new UserEsEntity();
            BeanUtils.copyProperties(user, userEsEntity);
            UserEsEntity save = userEsRepository.save(userEsEntity);
        }

        return user;
    }

    @Override
    public void handleMessage(String msg) {
        User user = JSON.parseObject(msg, User.class);
        userMongo.insert(user);
    }

    @Override
    public Page<User> getUserPage(UserDto userDto) {
        PageHelper.startPage(userDto.getPageNo(), userDto.getPageSize());
        List<User> userList = userDao.getUserPage(userDto);
        return PageUtils.getPage(userList);
    }

    @Override
    public User getUserEsInfo(String name) {
        QueryStringQueryBuilder queryStringQueryBuilder = new QueryStringQueryBuilder(name);
        Iterable<UserEsEntity> search = userEsRepository.search(queryStringQueryBuilder);
        Iterator<UserEsEntity> iterator = search.iterator();
        List<User> list = new ArrayList<>();
        while (iterator.hasNext()) {
            User user = new User();
            UserEsEntity userEsEntity = iterator.next();
            BeanUtils.copyProperties(userEsEntity, user);
            list.add(user);
        }
        return CollectionUtils.isEmpty(list) ? new User() : list.get(0);
    }
}
