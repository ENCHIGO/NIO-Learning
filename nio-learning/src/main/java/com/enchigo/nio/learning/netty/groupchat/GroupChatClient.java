package com.enchigo.nio.learning.netty.groupchat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

public class GroupChatClient {


    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup eventExecutors =new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(eventExecutors).channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();

                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());

                            pipeline.addLast(new GourpChatClientHandler());

                        }
                    });

            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 4564).sync();

            Channel channel = channelFuture.channel();

            Scanner scanner
                     = new Scanner(System.in);

            while (scanner.hasNext()){
                channel.writeAndFlush(scanner.nextLine());
            }
        } finally {
            eventExecutors.shutdownGracefully();
        }

    }

}
