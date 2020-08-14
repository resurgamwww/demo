package com.wht.demo.io;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * desc.
 *
 * @author wht
 */
public class TCPDemo {
    public static void main(String[] args) throws IOException {
        String ip="localhost";   //服务器端ip地址
        int port=8080;        //端口号
        Socket sck=new Socket(ip,port);
        //2.传输内容
        String content="what the fuck";
        byte[] bstream=content.getBytes(StandardCharsets.UTF_8);  //转化为字节流
        OutputStream os=sck.getOutputStream();   //输出流
        os.write(bstream);

        BufferedInputStream stream = new BufferedInputStream(sck.getInputStream());

        byte[] bytes = new byte[8192];
        int i = stream.read(bytes);

        System.out.println(new String(bytes));

        System.out.println();
        //3.关闭连接
        sck.close();
    }
}
