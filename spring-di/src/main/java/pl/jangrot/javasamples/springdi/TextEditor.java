package pl.jangrot.javasamples.springdi;

public class TextEditor {

    private SpellChecker spellChecker;

    public TextEditor(SpellChecker spellChecker) {
        this.spellChecker = spellChecker;
    }

    @Override
    public String toString() {
        return "TextEditor [spellChecker = " + spellChecker + "]";
    }
}
