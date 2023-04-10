package com.h2sm.smarthomehub.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.*;

import java.lang.reflect.Type;

@Slf4j
public class MyStompSessionHandler extends StompSessionHandlerAdapter {

    @Override
    public void afterConnected(StompSession session, StompHeaders headers) {
        System.out.println("Client connected: headers {}" + headers);
        session.subscribe("/user/queue/greetings", this);
        String message = "one-time message from client";
        log.info("Client sends: {}", message);
        session.send("/app/hello", new Message("bitch"));
    }
    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        try {
            var msg = ((Message) payload);
            var text = msg.getName();
            System.out.println(text);
        } catch (NullPointerException e) {
            System.out.println("Null pointer :(");
        }
        log.info("Client received: payload {}, headers {}", payload, headers);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Message.class;
    }

    @Override
    public void handleException(StompSession session, StompCommand command,
                                StompHeaders headers, byte[] payload, Throwable exception) {
        log.error("Client error: exception {}, command {}, payload {}, headers {}",
                exception.getMessage(), command, payload, headers);
    }
    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        log.error("Client transport error: error {}", exception.getMessage());
    }

}
