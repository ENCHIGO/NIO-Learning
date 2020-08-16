package com.enchigo.nio.learning.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FIleChannelReadTest {

    public static void main(String[] args) throws IOException {
        File file
                = new File("/data/fengjiaqi.conf");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

        fileChannel.read(byteBuffer);

        System.out.println(new String(byteBuffer.array()));

        fileChannel.close();

    }
}
