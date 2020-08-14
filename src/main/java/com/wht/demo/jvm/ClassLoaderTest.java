package com.wht.demo.jvm;

/**
 * desc.
 *
 * @author wht
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader cl = new MyClassLoader();
        Class<?> dispatch = cl.findClass("StaticDispatch");

    }
}
