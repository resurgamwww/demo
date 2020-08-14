package com.wht.demo.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * desc.
 *
 * @author wht
 */
public class AIODemo {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        //两种方式,异步阻塞和异步非阻塞

        ByteBuffer buf = ByteBuffer.allocate(20);
        buf.clear();

        AsynchronousFileChannel channelA = AsynchronousFileChannel.open(Paths.get("d:/testA.txt"));
        AsynchronousFileChannel channelB = AsynchronousFileChannel.open(Paths.get("d:/testB.txt"), StandardOpenOption.WRITE);

        Future<Integer> future = channelA.read(buf, 0);

        Integer i = future.get();

        buf.flip();
        CharBuffer charBuffer = CharBuffer.allocate(20);
        CharsetDecoder decoder = Charset.defaultCharset().newDecoder();
        decoder.decode(buf,charBuffer,true);

        charBuffer.flip();
        System.out.println(charBuffer.toString());

        buf.rewind();
        byte[] array = buf.array();

        System.out.println(channelB.size());
        Future<Integer> future1 = channelB.write(buf, channelB.size());
        Integer integer = future1.get();

        //while (i >= 0){
        //    buf.flip();
        //    channelB.write(buf,channelB.size());
        //}

        channelA.close();
        channelB.close();

        ThreadLocal<String> local = new ThreadLocal<String>();
        local.set("");
        String s = local.get();
        local.remove();

        AtomicInteger atomicInteger = new AtomicInteger(2);
        atomicInteger.incrementAndGet();

    }
}
