package com.h2sm.smarthomehub;

import com.h2sm.smarthomehub.socket.Message;
import lombok.SneakyThrows;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.stereotype.Component;

import java.io.ObjectOutputStream;
import java.net.URI;

@Component
public class MyWebSocketClient {//extends WebSocketClient {
//    public MyWebSocketClient(URI serverUri) {
//        super(serverUri);
//    }
//
//    @Override
//    @SneakyThrows
//    public void onOpen(ServerHandshake serverHandshake) {
//        System.out.println("onOpen: " + serverHandshake.getHttpStatusMessage());
//    }
//
//    @Override
//    @SneakyThrows
//    public void onMessage(String s) {
//        System.out.println("On message: " + s);
//        var outputStream = getSocket().getOutputStream();
//        var objectOutputStream = new ObjectOutputStream(outputStream);
//        var msg = new Message("Pezdeyshon");
//        objectOutputStream.writeObject(msg);
//    }
//
//    @Override
//    public void onClose(int i, String s, boolean b) {
//
//    }
//
//    @Override
//    public void onError(Exception e) {
//
//    }
//
//    @Override
//    public void send(String data) {
//        super.send(data);
//    }
}
