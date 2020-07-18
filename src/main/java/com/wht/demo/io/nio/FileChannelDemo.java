package com.wht.demo.io.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * desc.
 *
 * @author wht
 */
public class FileChannelDemo {
    public static void main(String[] args) {

        FileChannel channelA;

        String fileA = "D:/testA.txt";
        String fileB = "D:/testB.txt";

        try {
            channelA = new RandomAccessFile(fileA, "rw").getChannel();
            System.out.println(channelA.size());

            //FileChannel channelB = new FileInputStream(fileB).getChannel();
            FileChannel channelB = new FileOutputStream(fileB,true).getChannel();

            ByteBuffer buf = ByteBuffer.allocate(2);
            buf.clear();
            int i = channelA.read(buf);
            int count = 1;
            while (i >= 0){
                buf.flip();
                while(buf.hasRemaining()) {
                    System.out.println(count++);
                    channelB.write(buf);
                }
                buf.clear();
                i = channelA.read(buf);
            }

            channelA.close();
            channelB.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
