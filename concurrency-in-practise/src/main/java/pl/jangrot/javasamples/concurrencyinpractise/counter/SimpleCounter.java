package pl.jangrot.javasamples.concurrencyinpractise.counter;

public class SimpleCounter implements Counter {

    private long value;

    public long get() {
        return value;
    }

    public long increment() {
        if (value == Long.MAX_VALUE) {
            throw new IllegalStateException("Counter overflow");
        }
        return ++value;
    }
}
