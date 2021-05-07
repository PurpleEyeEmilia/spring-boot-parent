package com.github.user.mq;

import com.github.user.constants.UserConstant;
import com.github.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (c) 2017-2018 github Company LTD.
 * All rights reserved.
 *
 * @Description:
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
@Service
public class MessageListener {

    public static final Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);

    @Autowired
    private UserService userService;

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = UserConstant.USER_MESSAGE_QUEUE, durable = "true"),
            exchange = @Exchange(value = UserConstant.USER_MESSAGE_EXCHANGE, ignoreDeclarationExceptions = "true"),
            key = UserConstant.USER_MESSAGE_QUEUE))
    public void handleMessage(String msg){
        if (StringUtils.isNotBlank(msg)) {
            try {
                userService.handleMessage(msg);
            } catch (Exception e) {
                LOGGER.error("出消息失败！{}", msg, e);
            }
        }
    }

}
