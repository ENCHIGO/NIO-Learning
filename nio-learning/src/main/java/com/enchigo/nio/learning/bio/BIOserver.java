package com.enchigo.nio.learning.bio;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOserver {




    public static void main(String[] args) throws IOException {

        ExecutorService executorService = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(8181);

        System.out.println("服务器启动了 ");

        while(true){

           final Socket socket = serverSocket.accept();

           System.out.println("连接到一个客户端");


           executorService.execute(()->{

               handler(socket);


           });
        }

    }


    public static void handler(Socket socket) {
        System.out.println(Thread.currentThread().getName()+"开始处理请求");

        byte[] bytes = new byte[8];



        try(InputStream inputStream = socket.getInputStream();)
        {
            while (true){
            int read = inputStream.read(bytes);
            System.out.println(new String(bytes,0,read));
            if (read <= -1){
                break;
            }

            }
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("收到消息了，哈哈".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
