package pl.jangrot.javasamples.concurrencyinpractise.deadlock;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TryLockLockOrderingAvoidDeadlock {

    private static final Random rnd = new Random();
    private static final long FIXED_DELAY = 1;
    private static final long RANDOM_DELAY = 2;
    private static final long TIMEOUT = TimeUnit.SECONDS.toNanos(2);


    public boolean transfer(AccountWithLock fromAccount, AccountWithLock toAccount, BigDecimal amount) throws InterruptedException {
        long stopTime = System.nanoTime() + TIMEOUT;

        while (true) {
            if (fromAccount.lock.tryLock()) {
                try {
                    if (toAccount.lock.tryLock()) {
                        try {
                            if (fromAccount.getBalance().compareTo(amount) < 0) {
                                throw new IllegalStateException("Insufficient Funds");
                            }
                            fromAccount.debit(amount);
                            toAccount.credit(amount);
                            return true;
                        } finally {
                            toAccount.lock.unlock();
                        }
                    }
                } finally {
                    fromAccount.lock.unlock();
                }
            }
            if (System.nanoTime() > stopTime) {
                return false;
            }
            TimeUnit.NANOSECONDS.sleep(FIXED_DELAY + rnd.nextLong() % RANDOM_DELAY);
        }
    }
}
