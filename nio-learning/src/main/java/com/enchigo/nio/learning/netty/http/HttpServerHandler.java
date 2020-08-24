package com.enchigo.nio.learning.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

public class HttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {





    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {

        if(httpObject instanceof HttpRequest){

            HttpRequest httpRequest = (HttpRequest) httpObject;

            if (httpRequest.uri().contains("/favicon")) {
                System.out.println("不做相应");
                return;
            }
            ByteBuf byteBuf = Unpooled.copiedBuffer("hello,我订念什么大胜靠德", Charset.forName("utf-8"));

            DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);

            defaultFullHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");


            defaultFullHttpResponse.headers().set(HttpHeaderNames.CONTENT_ENCODING,"utf-8");


            defaultFullHttpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH,byteBuf.readableBytes());

            channelHandlerContext.writeAndFlush(defaultFullHttpResponse);


        }

    }
}
