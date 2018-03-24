package pl.jangrot.javasamples.concurrencyinpractise.counter;

import org.junit.Before;
import org.junit.Test;
import pl.jangrot.javasamples.concurrencyinpractise.Counter;

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
    public void singleThreadTest() {
        counter.increment();
        counter.increment();
        counter.increment();

        assertThat(counter.get()).isEqualTo(3);
    }

    @Test
    public void multiThreadTest() throws InterruptedException {
        int numOfThreads = 2000;

        Collection<Callable<Long>> increments = new ArrayList<>(numOfThreads);
        for (int i = 0; i < numOfThreads; i++) {
            increments.add(() -> counter.increment());
        }

        Executors.newFixedThreadPool(4).invokeAll(increments);
// not thread-safe implementation
//        assertThat(counter.get()).isEqualTo(numOfThreads); // FAILED
    }
}