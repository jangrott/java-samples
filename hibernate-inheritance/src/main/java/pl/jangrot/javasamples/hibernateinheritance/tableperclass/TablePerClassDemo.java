package pl.jangrot.javasamples.hibernateinheritance.tableperclass;

import org.hibernate.Session;
import pl.jangrot.javasamples.hibernateinheritance.HibernateUtils;

import java.util.Arrays;
import java.util.List;

public class TablePerClassDemo {

    public static void main(String[] args) {
        Session session = HibernateUtils.configureAndGetInstance(
                Arrays.asList(Person.class, Employee.class, Manager.class)
        ).getSession();

        session.beginTransaction();

        session.save(createPerson());
        session.save(createEmployee());
        session.save(createManager());

        session.getTransaction().commit();

        List<Person> persons = session.createQuery(
                "from pl.jangrot.javasamples.hibernateinheritance.tableperclass.Person"
        ).list();
        persons.forEach(System.out::println); // Person, Employee, Manager

        List<Manager> managers = session.createQuery(
                "from pl.jangrot.javasamples.hibernateinheritance.tableperclass.Manager"
        ).list();
        managers.forEach(System.out::println); // only Manager

        session.close();
    }

    private static Person createPerson() {
        Person person = new Person();
        person.setName("NameA");
        person.setSurname("SurnameA");
        return person;
    }

    private static Employee createEmployee() {
        Employee employee = new Employee();
        employee.setName("NameB");
        employee.setSurname("SurnameB");
        employee.setSalary(16000);

        return employee;
    }

    private static Manager createManager() {
        Manager manager = new Manager();
        manager.setName("NameC");
        manager.setSurname("SurnameC");
        manager.setSalary(21000);
        manager.setNumberOfsubordinates(10);

        return manager;
    }
}
