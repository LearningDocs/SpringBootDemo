package com.keepgulp.springbootactivemq.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: guodongqing
 * @create: 2018-08-06 20:16
 **/
@Component
public class TopicReceiver {
    @JmsListener(destination = "topicTest", containerFactory = "jmsTopicListenerContainerFactory")
    public void receive(String msg) {
        System.out.println("topicTest1 监听到的消息内容为: " + msg);
    }

    @JmsListener(destination = "topicTest", containerFactory = "jmsTopicListenerContainerFactory")
    public void receive2(String msg) {
        System.out.println("topicTest2 监听到的消息内容为: " + msg);
    }
}
