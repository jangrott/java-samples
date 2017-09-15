package pl.jangrot.javasamples.rxjava;

import io.reactivex.Flowable;

public class App {

    public static void main(String[] args) {
        Flowable.just("Hello world").subscribe(System.out::println);
    }
}
