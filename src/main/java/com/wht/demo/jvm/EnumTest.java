package com.wht.demo.jvm;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * desc.
 *
 * @author wht
 */
public enum  EnumTest implements Serializable {
    TEST("1","1"),
    TEST2("12","12");
    private String name;
    private String code;

    EnumTest(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:/test.txt"));
        //out.writeObject(EnumTest.TEST);
        //ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(new File("D:/test.txt")));
        //Object o = inputStream.readObject();
        //System.out.println(o.toString());

        HashMap<Object, Object> map = new LinkedHashMap<>();
        map.put("123", "123");
        map.put("12313123", "121");
        map.put("4", "5");
        map.put("66", "77");

        System.out.println(map.toString());

    }

    public static class Temp{
    }
}
