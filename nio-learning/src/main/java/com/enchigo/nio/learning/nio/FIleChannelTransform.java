package com.enchigo.nio.learning.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FIleChannelTransform {


    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream("/data/fengjiaqi.conf");

        FileOutputStream fileOutputStream = new FileOutputStream("/data/fengjiaqitransfer.conf");

        FileChannel sourceChannel = fileInputStream.getChannel();

        FileChannel targetChannel = fileOutputStream.getChannel();

        targetChannel.transferFrom(sourceChannel,0,sourceChannel.size());


    }

}
