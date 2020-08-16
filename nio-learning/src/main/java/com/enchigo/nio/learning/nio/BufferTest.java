package com.enchigo.nio.learning.nio;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HyperLogLogOperations;
import org.springframework.data.redis.core.RedisTemplate;
import sun.jvm.hotspot.memory.CMSBitMap;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.IntBuffer;
import java.nio.channels.SocketChannel;

public class BufferTest {



    public static void main(String[] args) throws IOException {

        IntBuffer intBuffer = IntBuffer.allocate(1024);

        Buffer buffer = intBuffer.flip();

        SocketChannel socketChannel = SocketChannel. open();



    }


}
