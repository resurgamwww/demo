package com.wht.demo.multithread;

import java.util.concurrent.*;

/**
 * desc.
 *
 * @author wht
 */
public class Memoizer<A, V> implements Computable<A, V> {

    private final ConcurrentMap<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memoizer(Computable<A, V> c) {
        this.c = c;
    }


    @Override
    public V compute(final A arg) throws InterruptedException {
        while (true) {
            Future<V> f = cache.get(arg);
            if (f == null) {
                Callable<V> eval = new Callable<V>() {
                    @Override
                    public V call() throws Exception {
                        return c.compute(arg);
                    }
                };
                FutureTask<V> futureTask = new FutureTask<>(eval);
                f = cache.putIfAbsent(arg, futureTask);
                if (f == null) {
                    f = futureTask;
                    futureTask.run();
                }
            }

            try {
                return f.get();
            } catch (CancellationException e) {
                cache.remove(arg, f);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
    }
}
