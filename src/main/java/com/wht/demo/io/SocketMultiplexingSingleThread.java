package com.wht.demo.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * desc.
 *
 * @author wht
 */
public class SocketMultiplexingSingleThread extends AbstractSocketMultiplexing {

    private ServerSocketChannel channel = null;
    //private Selector selector = null;

    public void init(int port) throws IOException {
        channel = ServerSocketChannel.open();
        channel.configureBlocking(false);
        channel.bind(new InetSocketAddress(port));

        selector = Selector.open();
        channel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void start() {
        try {
            init(9090);
            System.out.println("启动");

            while (true){
                execute();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        SocketMultiplexingSingleThread thread = new SocketMultiplexingSingleThread();
        thread.start();
    }


}
