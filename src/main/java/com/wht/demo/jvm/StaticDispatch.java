package com.wht.demo.jvm;

/**
 * desc.
 *
 * @author wht
 */
public class StaticDispatch {

    static class Human {
        public void say() {
            System.out.println("human");
        }
    }

    static class Man extends Human {
        @Override
        public void say() {
            System.out.println("man");
        }
    }

    static class Woman extends Human {
        @Override
        public void say() {
            System.out.println("woman");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        man.say();
        woman.say();
        man = new Woman();
        man.say();
    }
}
