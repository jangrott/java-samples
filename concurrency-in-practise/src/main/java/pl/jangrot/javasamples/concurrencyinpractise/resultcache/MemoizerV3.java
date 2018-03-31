package pl.jangrot.javasamples.concurrencyinpractise.resultcache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import static com.google.common.base.Throwables.throwIfUnchecked;

public class MemoizerV3<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>(); // thread-safe
    private final Computable<A, V> c;

    public MemoizerV3(Computable c) {
        this.c = c;
    }

    @Override
    public V compute(A input) throws InterruptedException { // no need to synchronize when accessing the cache
        Future<V> f = cache.get(input); // it is possible for two threads to call compute with the same value
        if (f == null) { // at roughly the same time, both see that the cache doesn't contain the desired value
            FutureTask<V> ft = new FutureTask<>(() -> c.compute(input)); // and both start computation
            f = ft;
            cache.put(input, ft);
            ft.run();
        }
        try {
            return f.get();
        } catch (ExecutionException e) {
            throwIfUnchecked(e);
            throw new RuntimeException(e);
        }
    }
}
