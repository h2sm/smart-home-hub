package com.h2sm.smarthomehub.socket;

import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

public class MyStompSessionHandler extends StompSessionHandlerAdapter {
//    @Override
//    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
//        System.out.println("New session established : " + session.getSessionId());
//        session.subscribe("/topic/messages", this);
//        session.send("/app/chat", getSampleMessage());
////        System.out.println("Subscribed to /messages/process");
////        session.send("/app/websocket-server", "getSampleMessage()");
////        System.out.println("Message sent to websocket server");
//    }
//
//    @Override
//    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
//        System.out.println("Got an exception");
//    }
//
////    @Override
////    public Type getPayloadType(StompHeaders headers) {
////        return Message.class;
////    }
//
//    @Override
//    public void handleFrame(StompHeaders headers, Object payload) {
//        Message msg = (Message) payload;
//        System.out.println("Received : " + msg.getText());
//    }
//
//    private Message getSampleMessage() {
//        Message msg = new Message();
//        msg.setText("Howdy!!");
//        return msg;
//    }
}
