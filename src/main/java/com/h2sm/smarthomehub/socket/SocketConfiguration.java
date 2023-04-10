package com.h2sm.smarthomehub.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.h2sm.smarthomehub.MyWebSocketClient;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.RestTemplateXhrTransport;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableWebSocket
public class SocketConfiguration {
//
//    private final static WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
//
//    @Bean
//    public MyStompSessionHandler sessionHandler(){
//        return new MyStompSessionHandler();
//    }

    @Bean
    public WebSocketStompClient webSocketClientHuynya() {
        var client = new StandardWebSocketClient();

        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        StompSessionHandler sessionHandler = new MyStompSessionHandler();
        stompClient.connect("ws://localhost:8082/hello", sessionHandler);
        stompClient.start();
       return null;
    }
    @Bean
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();
        resolver.setDefaultMimeType(MimeTypeUtils.APPLICATION_JSON);
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(new ObjectMapper());
        converter.setContentTypeResolver(resolver);
        messageConverters.add(converter);
        return false;
    }

//    @Bean
//    @SneakyThrows
//    public WebSocketStompClient webShit(){
//        StandardWebSocketClient webSocketClient = new StandardWebSocketClient();
//        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
//        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
//
//        String url = "ws://localhost/ws/hub?req=ok";
//        StompSessionHandler sessionHandler = new MyStompSessionHandler();
//        stompClient.connectAsync(url, sessionHandler);
//        return stompClient;
//    }

//    @Bean
//    @SneakyThrows
//    public URI myURI(){
//        return new URI("ws://localhost:8082/hello");
//    }
//
//    @Bean
//    public MyWebSocketClient webSocketClientBezStomp(){
//        var client = new MyWebSocketClient(myURI());
//        client.connect();
//        return client;
//    }
//    @Bean
//    public StompSessionHandler stompSessionHandler() {
//        return new MyStompSessionHandler();
//    }
//
//    @Bean
//    public WebSocketStompClient webSocketStompClient() {
//        var webSocketClient = webSocketClient();
//        WebSocketStompClient webSocketStompClient = new WebSocketStompClient(webSocketClient);
//        webSocketStompClient.setMessageConverter(new StringMessageConverter());
//        webSocketStompClient.connectAsync("ws://localhost:80/ws/hub?req=ok", stompSessionHandler());
//        return webSocketStompClient;
//    }
//    @Bean
//    public WebSocketClient webSocketClient() {
//        List<Transport> transports = new ArrayList<>();
//        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
//        transports.add(new RestTemplateXhrTransport());
//        return new SockJsClient(transports);
//    }

}
