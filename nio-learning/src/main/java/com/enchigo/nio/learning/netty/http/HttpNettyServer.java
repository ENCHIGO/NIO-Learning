package com.enchigo.nio.learning.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class HttpNettyServer {



    public static void main(String[] args) throws InterruptedException {


        NioEventLoopGroup bossGroup = new NioEventLoopGroup();

        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
            .childHandler(new HttpChannelInitializer());

            ChannelFuture channelFuture = serverBootstrap
                    .bind(6787).sync();



            ChannelFuture future = channelFuture.channel().closeFuture().sync();

            channelFuture.addListener(new GenericFutureListener() {
                @Override
                public void operationComplete(Future future) throws Exception {
                    if(channelFuture.isDone()){
                        System.out.println("服务器启动成功");
                    }
                }
            });


        } finally {
        bossGroup.shutdownGracefully();

        workerGroup.shutdownGracefully();
        }


    }
}
