package ch.zhaw.jtodo;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.zhaw.jtodo.dao.DAOFactory;
import ch.zhaw.jtodo.dao.ICategoryDAO;
import ch.zhaw.jtodo.dao.IDAOFactory;
import ch.zhaw.jtodo.domain.Category;

public class DAOTest {

	private SessionFactory sessionFactory;
	private IDAOFactory factory;
	
    @Before
    public void init() {
    	sessionFactory = new Configuration().configure("/hibernateTest.cfg.xml").buildSessionFactory();
        //sessionFactory = new Configuration().configure().buildSessionFactory(null);
        
    }
    
    @After
    public void clear() {
		if ( sessionFactory != null ) {
			sessionFactory.close();
		}
    }

	/*public void DOACategoryTest() {
		Category testCat1 = new Category();
		testCat1.setName("test1");
		
		this.factory = new DAOFactory(sessionFactory);
		
		ICategoryDAO catDAO = factory.getCategoryDAO();
		
		try {
			catDAO.write(testCat1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Category> resultList = catDAO.findAll();
		
		assertEquals(1,resultList.size());
	}*/
	

}
