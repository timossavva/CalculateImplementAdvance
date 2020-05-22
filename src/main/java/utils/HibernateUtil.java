package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private SessionFactory sessionFactory;

    public Session beginSessionTransaction() {
        sessionFactory = setUpSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        return session;
    }

    public SessionFactory setUpSessionFactory() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            return new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        return null;
    }

    public void shutdown() {
        sessionFactory.close();
    }


}
