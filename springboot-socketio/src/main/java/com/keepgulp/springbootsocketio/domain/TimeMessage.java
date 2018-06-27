package com.keepgulp.springbootsocketio.domain;

import java.io.Serializable;

/**
 * @description: 获取当前时间消息实体类
 * @author: guodongqing
 * @create: 2018-06-27 11:16
 **/
public class TimeMessage implements Serializable {
    private static final long serialVersionUID = 4098680584856164475L;
    private String id ;
    private String currentTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public String toString() {
        return "TimeMessage{" +
                "id='" + id + '\'' +
                ", currentTime='" + currentTime + '\'' +
                '}';
    }
}
