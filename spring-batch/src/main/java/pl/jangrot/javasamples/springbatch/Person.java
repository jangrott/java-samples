package pl.jangrot.javasamples.springbatch;

import javax.persistence.*;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;

    public Person() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return String.format("Person [name=%s, surname=%s]", name, surname);
    }

    private Person(Builder builder) {
        this.name = builder.name;
        this.surname = builder.surname;
    }

    public static class Builder {
        private String name;
        private String surname;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}
