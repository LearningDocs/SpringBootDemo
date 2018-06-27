package com.keepgulp.springbootsocketio.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @description: 请求控制器
 * @author: guodongqing
 * @create: 2018-06-27 11:31
 **/

@Controller
public class WsController {
    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public ServerResponseMessage say(ClientRequestMessage message) {
        System.out.println(">>>>>>>>>>>>>> " + message.getName());
        return new ServerResponseMessage("Welcome," + message.getName() + "!");
    }
}
