package pl.jangrot.javasamples.concurrencyinpractise.deadlock;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

public class TryLockLockOrderingAvoidDeadlockTest {

    private static final int NUM_OF_THREADS = 80000;

    private TryLockLockOrderingAvoidDeadlock underTest;

    @Before
    public void setUp() {
        underTest = new TryLockLockOrderingAvoidDeadlock();
    }

    @Test
    public void singleThread() throws InterruptedException {
        AccountWithLock fromAccount = new AccountWithLock();
        fromAccount.credit(BigDecimal.valueOf(100.04));

        AccountWithLock toAccount = new AccountWithLock();
        toAccount.credit(BigDecimal.valueOf(80.22));

        underTest.transfer(fromAccount, toAccount, BigDecimal.valueOf(21.99));

        assertThat(fromAccount.getBalance()).isEqualTo(BigDecimal.valueOf(78.05));
        assertThat(toAccount.getBalance()).isEqualTo(BigDecimal.valueOf(102.21));
    }

    @Test
    public void multiThread() throws InterruptedException {
        AccountWithLock fromAccount = new AccountWithLock();
        fromAccount.credit(BigDecimal.valueOf(1000000.55));

        AccountWithLock toAccount = new AccountWithLock();
        toAccount.credit(BigDecimal.valueOf(500000.55));

        Collection<Callable<Boolean>> tasks = new ArrayList<>(NUM_OF_THREADS);

        for (int i = 0; i < NUM_OF_THREADS; i++) {
            tasks.add(() -> underTest.transfer(fromAccount, toAccount, BigDecimal.valueOf(1.00)));
            tasks.add(() -> underTest.transfer(toAccount, fromAccount, BigDecimal.valueOf(2.00)));
        }

        Executors.newFixedThreadPool(4).invokeAll(tasks);

        assertThat(fromAccount.getBalance()).isEqualTo(BigDecimal.valueOf(1080000.55));
        assertThat(toAccount.getBalance()).isEqualTo(BigDecimal.valueOf(420000.55));
    }

    @Test(expected = IllegalStateException.class)
    public void throwsExceptionIfInsufficientFunds() throws InterruptedException {
        AccountWithLock accountWithZeroBalance = new AccountWithLock();
        AccountWithLock toAccount = new AccountWithLock();

        underTest.transfer(accountWithZeroBalance, toAccount, BigDecimal.valueOf(19.99));
    }
}