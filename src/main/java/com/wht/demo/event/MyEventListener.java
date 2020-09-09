package com.wht.demo.event;

import java.util.EventListener;

/**
 * desc.
 *
 * @author wht
 */
public interface MyEventListener extends EventListener {

    void handleEvent(MyEvent event);
}
