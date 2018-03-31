package pl.jangrot.javasamples.concurrencyinpractise.deadlock;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountWithLock {

    Lock lock = new ReentrantLock();

    private BigDecimal amount = BigDecimal.ZERO;

    void debit(BigDecimal amount) {
        this.amount = this.amount.subtract(amount);
    }

    void credit(BigDecimal amount) {
        this.amount = this.amount.add(amount);
    }

    BigDecimal getBalance() {
        return amount.setScale(2, RoundingMode.CEILING);
    }
}
