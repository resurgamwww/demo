package com.wht.demo.event;

/**
 * desc.
 *
 * @author wht
 */
public class MyEventHandler implements MyEventListener{

    @Override
    public void handleEvent(MyEvent event) {
        System.out.println("do something");
    }
}
