package com.enchigo.nio.learning.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelWriteReadTest {

    public static void main(String[] args) throws IOException {
        File file = new File("/data/fengjiaqi.conf");

        FileInputStream fileInputStream = new FileInputStream(file);

        FileChannel fileInputStreamChannel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("/data/fengjiaqiout.conf");

        FileChannel fileOutputStreamChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1);

        while (true){
            byteBuffer.clear();
            int read = fileInputStreamChannel.read(byteBuffer);
            if(read==-1){
                break;
            }
            byteBuffer.flip();
            fileOutputStreamChannel.write(byteBuffer);
        }

        fileInputStreamChannel.close();
        fileOutputStreamChannel.close();

    }

}
