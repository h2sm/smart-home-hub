package com.h2sm.smarthomehub.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.util.Scanner;
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
            String input = "Frank";
            Channel channel = f.sync().channel();
            channel.writeAndFlush(input);
            channel.flush();
            startChat(channel);

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            // Shut down the event loop to terminate all threads.
            group.shutdownGracefully();
        }
    }

    public static void startChat(Channel channel) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            var text = scanner.nextLine();
            channel.writeAndFlush(text);
            if (text.equals("q")) System.exit(0);
        }

    }
}

class ClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("Message from Server: " + msg);
    }
}