package pl.jangrot.javasamples.concurrencyinpractise.resultcache;

import java.util.Random;
import java.util.concurrent.TimeUnit;


public class ExpensiveFunction implements Computable<Integer, String> {

    private static Random rnd = new Random();

    @Override
    public String compute(Integer input) {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(rnd.nextInt(3)));
        } catch (InterruptedException e) {
            return "";
        }
        return String.valueOf(input);
    }
}
