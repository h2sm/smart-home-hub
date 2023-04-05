package com.h2sm.smarthomehub;

import com.h2sm.smarthomehub.socket.MyStompSessionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.Scanner;

@SpringBootApplication
public class SmarthomeHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmarthomeHubApplication.class, args);


    }

}
