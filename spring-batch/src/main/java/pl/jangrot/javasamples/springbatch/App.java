package pl.jangrot.javasamples.springbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.URL;

@SpringBootApplication
@EnableScheduling
public class App {

    private static final int TEN_SECONDS = 10000;
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static final String CREATE_PERSON_URL = "http://localhost:9000/create";

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);

        new Thread(createNewPersonEveryTenSeconds()).start();
    }

    private static Runnable createNewPersonEveryTenSeconds() {
        return () -> {
            while (true) {
                try {
                    new URL(CREATE_PERSON_URL).openStream();
                    Thread.sleep(TEN_SECONDS);
                } catch (Exception e) {
                    LOGGER.debug(e.getMessage());
                }
            }
        };
    }
}