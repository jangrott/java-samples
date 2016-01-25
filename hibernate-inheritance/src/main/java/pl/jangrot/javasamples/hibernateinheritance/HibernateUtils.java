package pl.jangrot.javasamples.hibernateinheritance;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateUtils {

    private static HibernateUtils instance;
    private SessionFactory sessionFactory;

    private HibernateUtils(List<Class<?>> annotatedClasses) {
        sessionFactory = configuration(annotatedClasses).buildSessionFactory();
    }

    public static HibernateUtils configureAndGetInstance(List<Class<?>> annotatedClasses) {
        if (instance == null) {
            instance = new HibernateUtils(annotatedClasses);
        }
        return instance;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

    private Configuration configuration(List<Class<?>> annotatedClasses) {
        Configuration configuration = new Configuration();
        annotatedClasses.forEach(configuration::addAnnotatedClass);

        return configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
                .setProperty("hibernate.connection.driver_class", "org.h2.Driver")
//                .setProperty("hibernate.connection.url", "jdbc:h2:mem:hibernatejavasamples") // In-Memory
                .setProperty("hibernate.connection.url", "jdbc:h2:./db/test") // Embedded
                .setProperty("hibernate.hbm2ddl.auto", "create")
                .setProperty("hibernate.show_sql", "true");
    }

}
