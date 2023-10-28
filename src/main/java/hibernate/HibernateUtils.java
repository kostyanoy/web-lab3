package hibernate;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

// hibernate session manager
public class HibernateUtils {
    @Getter
    private static SessionFactory sessionFactory; // generates sessions

    static {
        // apply configuration
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().loadProperties("hibernate.properties")
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static boolean isActive() {
        return sessionFactory != null;
    }
}
