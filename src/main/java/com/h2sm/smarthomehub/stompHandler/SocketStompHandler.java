package com.h2sm.smarthomehub.stompHandler;

import com.h2sm.smarthomehub.dtos.messages.Action;
import com.h2sm.smarthomehub.dtos.messages.Message;
import com.h2sm.smarthomehub.stompService.StompService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.*;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;

@Slf4j
@AllArgsConstructor
public class SocketStompHandler extends StompSessionHandlerAdapter {
    public static StompSession stompSession;
    private StompService service;

    @Override
    public void afterConnected(StompSession session, StompHeaders headers) {
        System.out.println("Client connected: headers {}" + headers);
        stompSession = session;
        session.subscribe("/user/queue/greetings", this);
//        String message = "one-time message from client";
//        log.info("Client sends: {}", message);
        session.send("/resp", new Action());
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        log.info("Client received: payload {}, headers {}", payload, headers);
        var actionMessage = ((Action) payload);
        var response = service.handleAction(actionMessage);
        sendData(response);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Action.class;
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

    public void sendData(Action action) {
        stompSession.send("/resp", action);
    }
}