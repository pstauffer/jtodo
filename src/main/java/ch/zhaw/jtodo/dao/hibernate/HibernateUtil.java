package ch.zhaw.jtodo.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Stellt Hilfsmethoden für Hibernate zur Verfügung
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static Session session;

    /**
     * Erstellt eine neue Factory um HibernateSessions zu kreieren
     * @return Erstellte SessionFactory
     */
    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Gibt aktuelle SessionFactory zurück
     * @return aktuelle SessionFactory
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    /**
     * Erstellt eine neue Session mithilfe der SessionFactory
     * @return neu erstellte Session
     */
    public static Session getSession(){
    	if(session == null){
    		session = sessionFactory.openSession();
    	}
    	
    	return session;
    }
    
    /**
     * Schliess eine geöffnete Session wieder
     */
    public static void closeSession(){
    	if(session != null){
    		session.close();
    	}
    }
}