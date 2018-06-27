package com.keepgulp.springbootsocketio.websocket;

/**
 * @description: 服务端像浏览器端发送消息封装类
 * @author: guodongqing
 * @create: 2018-06-27 11:25
 **/
public class ServerResponseMessage {

    private String responseMessage;

    public ServerResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {

        return responseMessage;
    }
}
