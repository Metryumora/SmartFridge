package snaige.smartfridge;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import snaige.smartfridge.entity.HistoryRecord;
import snaige.smartfridge.entity.User;

import javax.persistence.NoResultException;

/**
 * Created by Metr_yumora on 28.04.2017.
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    private static Session session;

    private static User activeUser;

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

    public static void shutdown() {
        getSessionFactory().close();
    }

    public static User getUserByName(String username) {
        try {
            return getSession().createQuery("from User u where u.name='" + username + "'", User.class).getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No results!");
            return null;
        }

    }

    public static User getActiveUser() {
        return activeUser;
    }

    public static void setActiveUser(User activeUser) {
        HibernateUtil.activeUser = activeUser;
    }

    public static boolean authUser(String login, String password) {
        User checkedUser = getUserByName(login);
        if (checkedUser != null && checkedUser.verifyPassword(password)) {
            setActiveUser(checkedUser);
            return true;
        } else
            return false;
    }

    public static void logOut() {
        activeUser = null;
    }
}
