package pl.jangrot.javasamples.concurrencyinpractise.resultcache;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MemoizerV4Test {

    private static final Random rnd = new Random();
    private static final int NUM_OF_THREADS = 2000;

    private ExpensiveFunction c = Mockito.spy(new ExpensiveFunction());

    private MemoizerV4<Integer, String> underTest;

    @Before
    public void setUp() {
        underTest = new MemoizerV4<>(c);
    }

    @Test
    public void multiThread() throws InterruptedException {
        Set<Integer> inputs = new HashSet<>();
        Collection<Callable<String>> tasks = new ArrayList<>(NUM_OF_THREADS);

        for (int i = 0; i < NUM_OF_THREADS; i++) {
            int input = rnd.nextInt(1000);
            inputs.add(input);
            tasks.add(() -> underTest.compute(input));
        }

        Executors.newFixedThreadPool(500).invokeAll(tasks);

        verify(c, times(inputs.size())).compute(anyInt());
    }

    @Test
    public void moreSophisticatedMultiThread() throws InterruptedException {
        Collection<Callable<String>> tasks = new ArrayList<>(NUM_OF_THREADS);

        for (int i = 0; i < NUM_OF_THREADS; i++) {
            tasks.add(() -> underTest.compute(1));
        }

        Executors.newFixedThreadPool(1000).invokeAll(tasks);

        verify(c, times(1)).compute(anyInt());
    }
}