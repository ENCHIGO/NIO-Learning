package com.enchigo.nio.learning.nio;

import io.netty.buffer.ByteBuf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelWriteTest {


    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("/data/fengjiaqi.conf",true);

        FileChannel fileChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        byteBuffer.put("fengjiaqi".getBytes());

        byteBuffer.flip();

        fileChannel.write(byteBuffer);

        fileChannel.close();

        fileOutputStream.close();

    }
}
