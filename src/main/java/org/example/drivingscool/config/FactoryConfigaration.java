package org.example.drivingscool.config;

import org.example.drivingscool.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FactoryConfigaration {

    private static FactoryConfigaration factoryConfiguration;
    private final SessionFactory sessionFactory;

    private FactoryConfigaration() {
        try {
            // Load hibernate.properties
            Properties properties = new Properties();
            try (InputStream inputStream = Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("hibernate1.properties")) {

                if (inputStream == null) {
                    throw new RuntimeException(" hibernate.properties not found in classpath");
                }

                properties.load(inputStream);
            }

            // Create Hibernate configuration
            Configuration configuration = new Configuration();
            configuration.addProperties(properties);

            // Register all entity classes

//            configuration.addAnnotatedClass(Student.class);
//            configuration.addAnnotatedClass(Instructor.class);
//            configuration.addAnnotatedClass(Payment.class);
            configuration.addAnnotatedClass(Course.class);
//            configuration.addAnnotatedClass(Lesson.class);

            // Build session factory
            sessionFactory = configuration.buildSessionFactory();

        } catch (IOException e) {
            throw new RuntimeException(" Failed to load hibernate.properties", e);
        }
    }

    // Singleton instance
    public static FactoryConfigaration getInstance() {
        if (factoryConfiguration == null) {
            factoryConfiguration = new FactoryConfigaration();
        }
        return factoryConfiguration;
    }

    // Get Hibernate session
    public Session getSession() {
        return sessionFactory.openSession();
    }
}
