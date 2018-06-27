package com.keepgulp.springbootsocketio;

import com.keepgulp.springbootsocketio.socketio.SocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan
public class SpringbootSocketioApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSocketioApplication.class, args);
        SocketServer server = new SocketServer();
        server.startServer();
    }
}
