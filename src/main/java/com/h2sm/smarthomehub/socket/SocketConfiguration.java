package com.h2sm.smarthomehub.socket;

import ch.qos.logback.core.net.server.Client;
import com.h2sm.smarthomehub.socket.MyStompSessionHandler;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@EnableWebSocket
public class SocketConfiguration {


}
