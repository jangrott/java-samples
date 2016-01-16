package pl.jangrot.javasamples.springdi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("pl.jangrot.javasamples.springdi")
public class TextEditorConfiguration {

    @Bean
    public SpellChecker englishSpellChecker() {
        return new EnglishSpellChecker();
    }

    @Bean
    public SpellChecker frenchSpellChecker() {
        return new FrenchSpellChecker();
    }

    @Bean
    public TextEditor textEditorWithEnglishSpellChecker() {
        return new TextEditor(englishSpellChecker());
    }

    @Bean
    public TextEditor textEditorWithFrenchSpellChecker() {
        return new TextEditor(frenchSpellChecker());
    }
}
