package pl.jangrot.javasamples.concurrencyinpractise.resultcache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoizerV2<A, V> implements Computable<A, V> {

    private final Map<A, V> cache = new ConcurrentHashMap<>(); // thread-safe
    private final Computable<A, V> c;

    public MemoizerV2(Computable c) {
        this.c = c;
    }

    @Override
    public V compute(A input) throws InterruptedException { // no need to synchronize when accessing the cache
        V result = cache.get(input); // computation for given input can be already in progress
        if (result == null) {
            result = c.compute(input);
            cache.put(input, result);
        }
        return result;
    }
}
