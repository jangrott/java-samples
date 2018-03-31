package pl.jangrot.javasamples.concurrencyinpractise.deadlock;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

public class LockOrderingAvoidDeadlockTest {

    private static final int NUM_OF_THREADS = 2000;

    private LockOrderingAvoidDeadlock underTest;

    @Before
    public void setUp() {
        underTest = new LockOrderingAvoidDeadlock();
    }

    @Test
    public void singleThread() {
        Account fromAccount = new Account();
        fromAccount.credit(BigDecimal.valueOf(100.04));

        Account toAccount = new Account();
        toAccount.credit(BigDecimal.valueOf(80.22));

        underTest.transfer(fromAccount, toAccount, BigDecimal.valueOf(21.99));

        assertThat(fromAccount.getBalance()).isEqualTo(BigDecimal.valueOf(78.05));
        assertThat(toAccount.getBalance()).isEqualTo(BigDecimal.valueOf(102.21));
    }

    @Test
    public void multiThread() throws InterruptedException {
        Account fromAccount = new Account();
        fromAccount.credit(BigDecimal.valueOf(1000000));

        Account toAccount = new Account();
        toAccount.credit(BigDecimal.valueOf(500000));

        Collection<Callable<Void>> tasks = new ArrayList<>(NUM_OF_THREADS);

        for (int i = 0; i < NUM_OF_THREADS; i++) {
            tasks.add(() -> underTest.transfer(fromAccount, toAccount, BigDecimal.valueOf(10.00)));
            tasks.add(() -> underTest.transfer(toAccount, fromAccount, BigDecimal.valueOf(20.00)));
        }

        Executors.newFixedThreadPool(4).invokeAll(tasks);
    }

    @Test(expected = IllegalStateException.class)
    public void throwsExceptionIfInsufficientFunds() {
        Account accountWithZeroBalance = new Account();
        Account toAccount = new Account();

        underTest.transfer(accountWithZeroBalance, toAccount, BigDecimal.valueOf(19.99));
    }
}