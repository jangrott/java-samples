package pl.jangrot.javasamples.springbatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;

@Controller
public class PersonController {

    @Autowired
    private PersonRepository repository;

    @RequestMapping("/create")
    public ResponseEntity<Void> create() {
        repository.save(createPersonWithRandomNameAndSurname());
        return ResponseEntity.ok().build();
    }

    private Person createPersonWithRandomNameAndSurname() {
        return new Person.Builder().withName(randomName()).withSurname(randomSurname()).build();
    }

    private String randomName() {
        return capitalizeFirstLetter(randomString(nextIntFromRange(3, 11)));
    }

    private String randomSurname() {
        return capitalizeFirstLetter(randomString(nextIntFromRange(4, 12)));
    }

    private String capitalizeFirstLetter(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    private String randomString(int length) {
        StringBuilder randomStringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            randomStringBuilder.append(randomCharFromSet());
        }

        return randomStringBuilder.toString();
    }

    private static final Random random = new Random();
    private static final String SET_OF_CHARS = "abcefghijklmnopqrstuvwxyz";
    private static final int SET_OF_CHARS_LENGTH = SET_OF_CHARS.length();

    private int nextIntFromRange(int min, int max) {
        return min + random.nextInt(max - min);
    }

    private char randomCharFromSet() {
        return SET_OF_CHARS.charAt(nextIntFromRange(0, SET_OF_CHARS_LENGTH));
    }
}
