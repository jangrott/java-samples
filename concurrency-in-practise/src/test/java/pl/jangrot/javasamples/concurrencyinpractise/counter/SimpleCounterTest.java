package pl.jangrot.javasamples.concurrencyinpractise.counter;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleCounterTest {

    private Counter counter;

    @Before
    public void setUp() {
        counter = new SimpleCounter();
    }

    @Test
    public void singleThread() {
        counter.increment();
        counter.increment();
        counter.increment();

        assertThat(counter.get()).isEqualTo(3);
    }

    @Ignore // not thread-safe implementation
    @Test
    public void multiThread() throws InterruptedException {
        int numOfThreads = 2000;

        Collection<Callable<Long>> increments = new ArrayList<>(numOfThreads);
        for (int i = 0; i < numOfThreads; i++) {
            increments.add(() -> counter.increment());
        }

        Executors.newFixedThreadPool(4).invokeAll(increments);

        assertThat(counter.get()).isEqualTo(numOfThreads); // FAILED
    }
}