package pl.jangrot.javasamples.springbatch;

import org.springframework.batch.item.ItemProcessor;

public class PersonProcessor implements ItemProcessor<Person, Person> {

    @Override
    public Person process(Person person) throws Exception {
        Person modifiedPerson = new Person();
        modifiedPerson.setName(person.getName().toUpperCase());
        modifiedPerson.setSurname(person.getSurname().toUpperCase());
        return modifiedPerson;
    }
}
