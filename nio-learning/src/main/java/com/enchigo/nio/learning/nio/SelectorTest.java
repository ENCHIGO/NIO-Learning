package com.enchigo.nio.learning.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class SelectorTest {

    public static void main(String[] args) throws Exception {


try {


        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        Selector selector = Selector.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(6666));

        serverSocketChannel.configureBlocking(false);

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){

            if(selector.select(1000)==0){
                System.out.println("服务器等待一秒钟，无连接");
                continue;
            }

            Set<SelectionKey> selectionKeySet = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeySet.iterator();

            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if (selectionKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                    socketChannel.read(buffer);
                    System.out.println("from客户端" + new String(buffer.array())+"channel为"+socketChannel.hashCode());
                }
                selectionKeySet.remove(selectionKey);
            }

        }

    }
    catch (Exception e){
    e.printStackTrace();
    }}


}
