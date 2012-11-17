package ch.zhaw.jtodo.dal;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ch.zhaw.jtodo.dal.hibernate.HibernateUtil;
import ch.zhaw.jtodo.domain.Category;
import ch.zhaw.jtodo.domain.Priority;

public class CRUDService implements ICRUDService {
	
	private SessionFactory factory;
	
	public CRUDService(){
		factory = HibernateUtil.getSessionFactory();
	}
	
	public void testCrudService(){
		Category cat = new Category();
		cat.setName("blaBlaBla");
		
		Priority prio = new Priority();
		prio.setName("minimal");
		
		writeToDb(cat);
		writeToDb(prio);
	}
	
	public <T> void  writeToDb(T businessObject){
		Session session = factory.openSession();
		session.beginTransaction();
		session.save(businessObject);
		session.getTransaction().commit();
		session.close();
	}
	
	public <T> void readFromDB(){
		
	}

}
