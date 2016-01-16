package pl.jangrot.javasamples.springioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TextEditor {

    private SpellChecker spellChecker;

    @Autowired
    public TextEditor(SpellChecker spellChecker) {
        this.spellChecker = spellChecker;
    }

    @Override
    public String toString() {
        return "TextEditor [spellChecker = " + spellChecker + "]";
    }
}
