package com.wht.demo.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * desc.
 *
 * @author wht
 */
public abstract class AbstractSocketMultiplexing {

    protected Selector selector = null;

    public AbstractSocketMultiplexing() {
    }

    public AbstractSocketMultiplexing(Selector selector) {
        this.selector = selector;
    }

    public void execute() throws IOException {
        while (selector.select(0) > 0) {
            Set<SelectionKey> keys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = keys.iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();

                if (key.isAcceptable()) {
                    acceptHandler(key);
                } else if (key.isReadable()) {
                    readHandler(key);
                }
            }

        }
    }

    public void acceptHandler(SelectionKey key) {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        try {
            SocketChannel client = ssc.accept();

            client.configureBlocking(false);
            ByteBuffer buffer = ByteBuffer.allocate(8192);
            client.register(selector, SelectionKey.OP_READ, buffer);

            System.out.println("客户端建立了连接:" + client.getRemoteAddress() + "," + client.getLocalAddress());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readHandler(SelectionKey key) {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.clear();
        int read = 0;
        try {
            while (true) {
                read = channel.read(buffer);
                if (read > 0) {
                    buffer.flip();
                    channel.write(buffer);
                    buffer.clear();
                } else if (read == 0) {
                    break;
                } else {
                    // -1 close_wait 如果不关闭，在unix系统下会出现死循环，导致CPU占用升高
                    channel.close();
                    break;
                }
            }
        } catch (IOException e) {
            key.cancel();
            e.printStackTrace();
            try {
                channel.close();
            } catch (IOException ioException) {
            }
        }

    }
}
