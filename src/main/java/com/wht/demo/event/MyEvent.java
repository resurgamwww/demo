package com.wht.demo.event;

import java.util.EventObject;

/**
 * desc.
 *
 * @author wht
 */
public class MyEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public MyEvent(Object source) {
        super(source);
    }
}
