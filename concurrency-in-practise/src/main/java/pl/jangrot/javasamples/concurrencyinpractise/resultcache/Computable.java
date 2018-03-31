package pl.jangrot.javasamples.concurrencyinpractise.resultcache;

public interface Computable<A, V> {
    V compute(A input) throws InterruptedException;
}
