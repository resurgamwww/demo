package com.wht.demo.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * desc.
 *
 * @author wht
 */
public class BIODemo {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(8080);
        while (true) {
            Socket client = socket.accept();
            new Thread(() -> {
                try {
                    InputStream inputStream = client.getInputStream();
                    OutputStream outputStream = client.getOutputStream();
                    int i = inputStream.read();
                    outputStream.write(i);
                    outputStream.flush();
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
