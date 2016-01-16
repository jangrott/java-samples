package pl.jangrot.javasamples.springioc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Demo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(TextEditorConfiguration.class);
        context.refresh();

        TextEditor textEditor = context.getBean(TextEditor.class);

        System.out.println(textEditor);
    }
}