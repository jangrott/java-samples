package pl.jangrot.javasamples.concurrencyinpractise.resultcache;

import java.util.HashMap;
import java.util.Map;

public class MemoizerV1<A, V> implements Computable<A, V> {

    private final Map<A, V> cache = new HashMap(); // not thread-safe
    private final Computable<A, V> c;

    public MemoizerV1(Computable c) {
        this.c = c;
    }

    @Override
    public synchronized V compute(A input) throws InterruptedException { // synchronizing the entire method => obvious scalability problem
        V result = cache.get(input);
        if (result == null) {
            result = c.compute(input);
            cache.put(input, result);
        }
        return result;
    }
}
