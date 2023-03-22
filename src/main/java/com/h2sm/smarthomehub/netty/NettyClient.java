package com.h2sm.smarthomehub.netty;

import com.h2sm.smarthomehub.device.DeviceDTO;
import com.h2sm.smarthomehub.device.ewelinkBulbs.RequestJSONs;
import com.h2sm.smarthomehub.device.ewelinkBulbs.TurnON;
import com.h2sm.smarthomehub.service.PostService;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class NettyClient {
    static final String HOST = "127.0.0.1";
    static final int PORT = 8099;


    public static void main(String[] args) throws Exception {

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group) // Set EventLoopGroup to handle all eventsf for client.
                    .channel(NioSocketChannel.class)// Use NIO to accept new connections.
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new StringDecoder());
                            p.addLast(new StringEncoder());
                            // This is our custom client handler which will have logic for chat.
                            p.addLast(new ClientHandler());

                        }
                    });
            // Start the client.
            ChannelFuture f = b.connect(HOST, PORT).sync();
            //String input = "AUTH_HUB:666";
            Channel channel = f.sync().channel();
//            channel.writeAndFlush(input);
//            channel.flush();
//            startChat(channel);

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            // Shut down the event loop to terminate all threads.
            group.shutdownGracefully();
        }
    }

//    public static void startChat(Channel channel) {
//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            var text = scanner.nextLine();
//            channel.writeAndFlush(text);
//            if (text.equals("q")) System.exit(0);
//        }
//
//    }
}

class ClientHandler extends SimpleChannelInboundHandler<String> {
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("Message from Server: " + msg);

        var parsedMessage = parseMessage(msg);
        switch (parsedMessage.get(0)) {
            case "TURN_ON":
                switchState(parsedMessage.get(1), true);
                break;
            case "TURN_OFF":
                switchState(parsedMessage.get(1), false);
                break;
            case "CHANGE_COLOR":
                changeColor(parsedMessage);
                break;
            case "AUTH":
                authHub(ctx);
                break;
            default:
                System.out.println("UNKNOWN MESSAGE: " + msg);
                break;
        }
    }

    private void authHub(ChannelHandlerContext ctx){
        ctx.channel().writeAndFlush("AUTH_HUB:666");
    }

    private List<String> parseMessage(String msg) {
        return List.of(msg.split(":"));
    }

    public void switchState(String ip, boolean isOn) {
        String url = "http://" + ip + ":8081/zeroconf/switch";
        String requestJson = isOn ? RequestJSONs.TURN_ON : RequestJSONs.TURN_OFF;
        sendCommand(requestJson, url);
    }

    public void changeColor(List<String> msg) {
        String url = "http://" + msg.get(1) + ":8081/zeroconf/dimmable";
        String requestJson = RequestJSONs.changeColor(msg);
        sendCommand(requestJson, url);
    }

    private void sendCommand(String requestJson, String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        String answer = restTemplate.postForObject(url, entity, String.class);
        System.out.println(answer);
    }
}