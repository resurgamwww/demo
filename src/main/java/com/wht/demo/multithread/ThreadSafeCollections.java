package com.wht.demo.multithread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * desc.
 *
 * @author wht
 */
public class ThreadSafeCollections {
    public void list(){
        Vector<Object> objects = new Vector<>();

        List<Object> synchronizedList = Collections.synchronizedList(new ArrayList<>());

        CopyOnWriteArrayList<Object> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

        //ConcurrentLinkedDeque

    }
}
