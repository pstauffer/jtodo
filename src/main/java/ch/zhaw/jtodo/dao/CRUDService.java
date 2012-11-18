package ch.zhaw.jtodo.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch.zhaw.jtodo.dao.hibernate.HibernateUtil;
import ch.zhaw.jtodo.domain.Category;
import ch.zhaw.jtodo.domain.Priority;

public class CRUDService {
	
	private SessionFactory factory;
	
	public CRUDService(){
		factory = HibernateUtil.getSessionFactory();
	}
	
	/**
	 * Obsolete class, just for inital tests
	 */
	public void testCrudService(){
		Category cat = new Category();
		
		cat.setName("test1");
		
		ICategoryDAO catDAO = DAOFactory.getInstance().getCategoryDAO();
		 
		try {
			Category cat3 = catDAO.write(cat);
			Category cat2 = catDAO.findById(1);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Category> catList = catDAO.findAll();
		
	}
	

}
