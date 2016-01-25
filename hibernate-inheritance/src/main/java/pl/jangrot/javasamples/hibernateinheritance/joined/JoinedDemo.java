package pl.jangrot.javasamples.hibernateinheritance.joined;

import org.hibernate.Session;
import pl.jangrot.javasamples.hibernateinheritance.HibernateUtils;

import java.util.Arrays;
import java.util.List;

public class JoinedDemo {

    public static void main(String[] args) {
        Session session = HibernateUtils.configureAndGetInstance(
                Arrays.asList(Car.class, Motorbike.class)
        ).getSession();

        session.beginTransaction();

        session.save(createCar());
        session.save(createMotorbike());

        session.getTransaction().commit();

        List<Vehicle> cars = session.createQuery("from pl.jangrot.javasamples.hibernateinheritance.joined.Vehicle").list();
        cars.forEach(System.out::println);

        session.close();
    }

    private static Car createCar() {
        Car car = new Car();
        car.setMake("BMW");
        car.setModel("530d (F10)");
        car.setNumberOfDoors(5);

        return car;
    }

    private static Motorbike createMotorbike() {
        Motorbike motorbike = new Motorbike();
        motorbike.setMake("Suzuki");
        motorbike.setModel("GSX-R 1000");
        motorbike.setNumberOfStands(2);

        return motorbike;
    }
}
