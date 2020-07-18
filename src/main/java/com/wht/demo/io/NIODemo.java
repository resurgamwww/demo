package com.wht.demo.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * desc.
 *
 * @author wht
 */
public class NIODemo {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(8080));
        ssc.configureBlocking(false);

        List<SocketChannel> clients = new ArrayList<>();
        while (true) {

            SocketChannel client = ssc.accept();
            if(client == null){

            } else {
                client.configureBlocking(false);
                clients.add(client);
            }

            ByteBuffer buffer = ByteBuffer.allocate(4096);

            for (SocketChannel c : clients) {
                int num = c.read(buffer);
                if (num > 0){
                    buffer.flip();
                    byte[] bytes = new byte[buffer.limit()];
                    buffer.get(bytes);

                    String s = new String(bytes);
                    System.out.println(c.socket().getPort() + "s");
                    buffer.clear();
                }
            }


        }
    }

    public void test(){

    }
}
