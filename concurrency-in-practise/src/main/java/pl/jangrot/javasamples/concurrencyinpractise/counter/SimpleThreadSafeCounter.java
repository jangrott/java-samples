package pl.jangrot.javasamples.concurrencyinpractise.counter;

public class SimpleThreadSafeCounter implements Counter {

    private long value;

    public synchronized long get() {
        return value;
    }

    public synchronized long increment() {
        if (value == Long.MAX_VALUE) {
            throw new IllegalStateException("Counter overflow");
        }
        return ++value;
    }
}
