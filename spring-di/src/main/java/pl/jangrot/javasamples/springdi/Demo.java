package pl.jangrot.javasamples.springdi;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Demo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(TextEditorConfiguration.class);
        context.refresh();

        Arrays.stream(context.getBeanDefinitionNames()).forEach(
                System.out::println
        );

        TextEditor textEditorWithEnglishSpellChecker
                = (TextEditor) context.getBean("textEditorWithEnglishSpellChecker");

        TextEditor textEditorWithFrenchSpellChecker
                = (TextEditor) context.getBean("textEditorWithFrenchSpellChecker");

        System.out.println(textEditorWithEnglishSpellChecker);
        System.out.println(textEditorWithFrenchSpellChecker);
    }
}
