package pl.jangrot.javasamples.concurrencyinpractise.resultcache;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

public class MemoizerV1Test {

    private static final int NUM_OF_THREADS = 2000;

    private MemoizerV1<String, String> underTest;

    @Before
    public void setUp() {
        underTest = new MemoizerV1<>(new ExpensiveFunction());
    }

    @Ignore // it takes a long time to finish
    @Test
    public void multiThread() throws InterruptedException {
        Collection<Callable<String>> tasks = new ArrayList<>(NUM_OF_THREADS);

        for (int i = 0; i < NUM_OF_THREADS; i++) {
            tasks.add(() -> underTest.compute(UUID.randomUUID().toString()));
        }

        Executors.newFixedThreadPool(500).invokeAll(tasks);
    }
}