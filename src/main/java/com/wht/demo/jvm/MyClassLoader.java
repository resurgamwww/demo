package com.wht.demo.jvm;

/**
 * desc.
 *
 * @author wht
 */
public class MyClassLoader extends ClassLoader{

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("myClassLoader");
        return super.findClass(name);
    }
}
