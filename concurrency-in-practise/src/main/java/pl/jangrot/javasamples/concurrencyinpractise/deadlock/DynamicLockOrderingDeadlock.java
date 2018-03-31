package pl.jangrot.javasamples.concurrencyinpractise.deadlock;

import java.math.BigDecimal;

public class DynamicLockOrderingDeadlock {

    public Void transfer(Account fromAccount, Account toAccount, BigDecimal amount) {
        synchronized (fromAccount) {
            synchronized (toAccount) {
                if (fromAccount.getBalance().compareTo(amount) < 0) {
                    throw new IllegalStateException("Insufficient Funds");
                }
                fromAccount.debit(amount);
                toAccount.credit(amount);
            }
        }

        return null;
    }
}
