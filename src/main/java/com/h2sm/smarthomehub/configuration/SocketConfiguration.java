package com.h2sm.smarthomehub.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.h2sm.smarthomehub.stompHandler.SocketStompHandler;
import com.h2sm.smarthomehub.stompService.StompService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.List;

@Configuration
@EnableWebSocket
@AllArgsConstructor
public class SocketConfiguration {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @Bean
    public StompService service(){
        return new StompService(restTemplate());
    }


    @Bean
    public WebSocketStompClient webSocketClient() {
        var d = AuthRequest.authHub();

        var client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        StompSessionHandler sessionHandler = new SocketStompHandler(service());
        stompClient.connect("ws://localhost:8082/hello?token=" + d.getToken(), sessionHandler);

        stompClient.start();
        return stompClient;
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

}
