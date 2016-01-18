package pl.jangrot.javasamples.hibernateinheritance;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.jangrot.javasamples.hibernateinheritance.singletable.Circle;
import pl.jangrot.javasamples.hibernateinheritance.singletable.Rectangle;
import pl.jangrot.javasamples.hibernateinheritance.singletable.Square;

public class HibernateUtils {

    private static HibernateUtils instance;
    private SessionFactory sessionFactory;

    private HibernateUtils() {
        sessionFactory = configuration().buildSessionFactory();
    }

    public static HibernateUtils getInstance() {
        if (instance == null) {
            instance = new HibernateUtils();
        }
        return instance;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

    private Configuration configuration() {
        return new Configuration()
                .addAnnotatedClass(Circle.class)
                .addAnnotatedClass(Rectangle.class)
                .addAnnotatedClass(Square.class)
                .setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
                .setProperty("hibernate.connection.driver_class", "org.h2.Driver")
                .setProperty("hibernate.connection.url", "jdbc:h2:mem:hibernatejavasamples") // In-Memory
//                .setProperty("hibernate.connection.url", "jdbc:h2:./db/test") // Embedded
                .setProperty("hibernate.hbm2ddl.auto", "create")
                .setProperty("hibernate.show_sql", "true");
    }

}
