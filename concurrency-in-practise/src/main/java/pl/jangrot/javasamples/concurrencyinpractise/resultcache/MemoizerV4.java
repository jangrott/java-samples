package pl.jangrot.javasamples.concurrencyinpractise.resultcache;

import java.util.Map;
import java.util.concurrent.*;

import static com.google.common.base.Throwables.throwIfUnchecked;

public class MemoizerV4<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>(); // thread-safe
    private final Computable<A, V> c;

    public MemoizerV4(Computable c) {
        this.c = c;
    }

    @Override
    public V compute(A input) throws InterruptedException { // no need to synchronize when accessing the cache
        while (true) {
            Future<V> f = cache.get(input);
            if (f == null) {
                FutureTask<V> ft = new FutureTask<>(() -> c.compute(input));
                f = cache.putIfAbsent(input, ft);
                if (f == null) { // run the computation as future for given key doesn't exist
                    f = ft;
                    ft.run();
                }
            }
            try {
                return f.get();
            } catch (CancellationException e) {
                cache.remove(input, f); // removes the feature from the cache if computation was cancelled
            } catch (ExecutionException e) {
                throwIfUnchecked(e);
                throw new RuntimeException(e);
            }
        }
    }
}
