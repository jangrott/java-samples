package pl.jangrot.javasamples.concurrencyinpractise.deadlock;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Account {

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
