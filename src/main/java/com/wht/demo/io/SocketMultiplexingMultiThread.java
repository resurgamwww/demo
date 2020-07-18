package com.wht.demo.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * desc.
 *
 * @author wht
 */
public class SocketMultiplexingMultiThread {

    static ServerSocketChannel server = null;
    static Selector s1 = null;
    static Selector s2 = null;
    static Selector s3 = null;

    static int selectorCount = 2;
    static AtomicInteger idx = new AtomicInteger();
    static BlockingQueue<SocketChannel>[] queue = new LinkedBlockingQueue[selectorCount];

    {
        for (int i = 0, queueLength = queue.length; i < queueLength; i++) {
            queue[i] = new LinkedBlockingQueue<>();
        }

    }

    int port = 9090;

    public void init() throws IOException {
        server = ServerSocketChannel.open();
        server.configureBlocking(false);
        server.bind(new InetSocketAddress(port));

        s1 = Selector.open();
        s2 = Selector.open();
        s3 = Selector.open();

        server.register(s1, SelectionKey.OP_ACCEPT);
    }

    public static void main(String[] args) throws IOException {

        SocketMultiplexingMultiThread service = new SocketMultiplexingMultiThread();
        service.init();

        new Thread(new NioThread(s1, 2)).start();
        new Thread(new NioThread(s2)).start();
        new Thread(new NioThread(s3)).start();

    }


}

class NioThread extends AbstractSocketMultiplexing implements Runnable {
    int id = 0;

    boolean isBoss = false;

    public NioThread(Selector selector, int id) {
        super(selector);
        this.id = id;
        this.isBoss = true;
        System.out.printf("Boss %d start.", this.id);
    }

    public NioThread(Selector selector) {
        super(selector);
        this.id = SocketMultiplexingMultiThread.idx.getAndIncrement() % SocketMultiplexingMultiThread.selectorCount;
        System.out.printf("Worker %d start.", this.id);
    }

    @Override
    public void run() {

        try {
            if (isBoss){
                while (true) {
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
            } else {
                while (true) {


                    while (selector.select(10) > 0) {
                        Set<SelectionKey> keys = selector.selectedKeys();

                        Iterator<SelectionKey> iterator = keys.iterator();

                        while (iterator.hasNext()) {
                            SelectionKey key = iterator.next();
                            iterator.remove();

                            if (key.isReadable()) {
                                readHandler(key);
                            }
                        }

                    }

                    if (!SocketMultiplexingMultiThread.queue[id].isEmpty()) {
                        ByteBuffer buffer = ByteBuffer.allocate(8192);
                        SocketChannel client = SocketMultiplexingMultiThread.queue[id].take();
                        client.register(selector, SelectionKey.OP_READ, buffer);
                        System.out.println("新客户端：" + client.socket().getPort() + "分配到worker:" + id);
                    }


                }
            }
        } catch (Exception e) {

        }

    }

    public void acceptHandler(SelectionKey key) {
        try {
            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
            SocketChannel client = ssc.accept();
            client.configureBlocking(false);

            int num = SocketMultiplexingMultiThread.idx.getAndIncrement() % SocketMultiplexingMultiThread.selectorCount;
            SocketMultiplexingMultiThread.queue[num].add(client);

            System.out.println("客户端建立了连接:" + client.getRemoteAddress() + "," + client.getLocalAddress());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


