package ch.zhaw.jtodo.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Preparation {
	
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory(){
		if(sessionFactory==null){
			Configuration configuration = new Configuration();
			configuration.configure("testConfig.xml");
			sessionFactory = configuration.buildSessionFactory();
		}
		return sessionFactory;
	}
	
	public static void closeSessionFactory(){
		sessionFactory.close();
		sessionFactory = null;
	}

}
