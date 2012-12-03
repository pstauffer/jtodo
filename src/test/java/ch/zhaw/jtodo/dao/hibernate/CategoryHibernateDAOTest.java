package ch.zhaw.jtodo.dao.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import ch.zhaw.jtodo.domain.Category;

public class CategoryHibernateDAOTest {

	private CategoryHibernateDAO catDAO;
	
	@Before
	public void setUp() throws Exception {
		  // setup the session factory
		Configuration configuration = new Configuration();
		configuration.configure("testConfig.xml");
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		
		catDAO = new CategoryHibernateDAO();
		catDAO.setFactory(sessionFactory);
	}

	@Test
	public void test() {
		Category cat = new Category();
		
		cat.setName("test1");
		//cat.setId(1);
		
		try {
			
			catDAO.write(cat);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Category> catList = catDAO.findAll();
	}

}
