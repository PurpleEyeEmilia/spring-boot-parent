package com.github.user.mq;

import com.github.user.constants.UserConstant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
public class MessageSender {

    public static final Logger LOGGER = LoggerFactory.getLogger(MessageSender.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //Direct直连交换机
    public void sendMessage(String msg) {
        if (StringUtils.isNotBlank(msg)) {
            rabbitTemplate.setExchange(UserConstant.USER_MESSAGE_EXCHANGE);
            rabbitTemplate.setRoutingKey(UserConstant.USER_MESSAGE_QUEUE);
            try {
                LOGGER.info("开始发送消息！{}", msg);
                rabbitTemplate.convertAndSend(msg);
                LOGGER.info("发送消息成功！{}", msg);
            } catch (Exception e) {
                LOGGER.error("发送消息异常！{}", msg, e);
            }
        }
    }

}
