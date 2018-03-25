package pl.jangrot.javasamples.concurrencyinpractise.counter;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongCounter implements Counter {

    private AtomicLong value = new AtomicLong();

    @Override
    public long get() {
        return value.get();
    }

    @Override
    public long increment() {
        return value.incrementAndGet();
    }
}
