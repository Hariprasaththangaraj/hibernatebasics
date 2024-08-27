package org.example.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.example.entity.InboundResponse;
import org.example.entity.InboundRequest;
import org.example.entity.LesseName;

public class HibernateConfig {
    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration()
                    .configure()  // Load hibernate.cfg.xml
                    .addAnnotatedClass(InboundResponse.class)  // Add entity classes
                    .addAnnotatedClass(InboundRequest.class)
                    .addAnnotatedClass(LesseName.class);

            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
