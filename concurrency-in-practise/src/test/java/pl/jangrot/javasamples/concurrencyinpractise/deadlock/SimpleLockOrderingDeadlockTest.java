package pl.jangrot.javasamples.concurrencyinpractise.deadlock;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

public class SimpleLockOrderingDeadlockTest {

    private SimpleLockOrderingDeadlock underTest;

    @Before
    public void setUp() {
        underTest = new SimpleLockOrderingDeadlock();
    }

    @Ignore // This test should fails as it will take more than 3 second due to deadlock
    @Test(timeout = 3000)
    public void multiThread() throws InterruptedException {
        int numOfThreads = 1000;

        Collection<Callable<Void>> tasks = new ArrayList<>(numOfThreads);

        for (int i = 0; i < numOfThreads; i++) {
            tasks.add(underTest::leftRight);
            tasks.add(underTest::rightLeft);
        }

        Executors.newFixedThreadPool(4).invokeAll(tasks);
    }

}