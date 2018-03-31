package pl.jangrot.javasamples.concurrencyinpractise.deadlock;

import java.math.BigDecimal;

public class FundTransfer {

    private static final Object TIE_LOCK = new Object();

    public Void transfer(Account fromAccount, Account toAccount, BigDecimal amount) {

        class Helper {
            public void transfer() {
                if (fromAccount.getBalance().compareTo(amount) < 0) {
                    throw new IllegalStateException("Insufficient Funds");
                }
                fromAccount.debit(amount);
                toAccount.credit(amount);
            }
        }

        int fromHash = System.identityHashCode(fromAccount);
        int toHash = System.identityHashCode(toAccount);

        if (fromHash < toHash) {
            synchronized (fromAccount) {
                synchronized (toAccount) {
                    new Helper().transfer();
                }
            }
        } else if (fromHash > toHash) {
            synchronized (toAccount) {
                synchronized (fromAccount) {
                    new Helper().transfer();
                }
            }
        } else {
            synchronized (TIE_LOCK) {
                synchronized (fromAccount) {
                    synchronized (toAccount) {
                        new Helper().transfer();
                    }
                }
            }
        }

        return null;
    }
}