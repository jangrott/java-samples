package pl.jangrot.javasamples.concurrencyinpractise.deadlock;

public class SimpleLockOrderingDeadlock {

    private final Object left = new Object();
    private final Object right = new Object();

    public Void leftRight() {
        synchronized (left) {
            synchronized (right) {
                doSomething();
            }
        }
        return null;
    }

    public Void rightLeft() {
        synchronized (right) {
            synchronized (left) {
                doSomething();
            }
        }
        return null;
    }

    private void doSomething() {
    }

}
