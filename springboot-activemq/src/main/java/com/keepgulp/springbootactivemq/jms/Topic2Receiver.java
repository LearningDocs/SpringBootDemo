package com.keepgulp.springbootactivemq.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @description: 可监听历史消息
 * @author: guodongqing
 * @create: 2018-08-06 20:17
 **/
@Component
public class Topic2Receiver {
    @JmsListener(destination = "topicTest", containerFactory = "jmsTopicListenerContainerFactory2")
    public void receive(String msg) {
        System.out.println("这是持久订阅: " + msg);
    }
}
