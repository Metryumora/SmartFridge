package snaige.smartfridge;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by Metr_yumora on 28.04.2017.
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    private static Session session;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null)
            try {
                Configuration configuration = new Configuration().addResource("hibernate.cfg.xml");
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(HistoryRecord.class);
                configuration.configure();
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                        configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Throwable ex) {
                throw new ExceptionInInitializerError(ex);
            }
        return sessionFactory;
    }


    public static Session getSession() {
        if (session == null)
            session = getSessionFactory().openSession();
        return session;
    }

    public static void setSession(Session session) {
        HibernateUtil.session = session;
    }


    public static void shutdown() {
        getSessionFactory().close();
    }

    public static User getUserByName(String name) {
        User user;
        user = getSession().createQuery("from User where name = " + name, User.class).uniqueResult();
        return user;
    }

}
