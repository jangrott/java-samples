package pl.jangrot.javasamples.springioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("pl.jangrot.javasamples.springioc")
public class TextEditorConfiguration {

    @Bean
    public SpellChecker spellChecker() {
        return new EnglishSpellChecker();
    }

}
