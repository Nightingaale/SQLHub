import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class Connection {
    private static final SessionFactory sf = new Configuration().configure().buildSessionFactory();

    private static SessionFactory getSessionFactory() {
        try {
            return new Configuration().configure(new File("src/main/resources/hibernate.cfg.xml")).buildSessionFactory();
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSF() {
        return sf;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}