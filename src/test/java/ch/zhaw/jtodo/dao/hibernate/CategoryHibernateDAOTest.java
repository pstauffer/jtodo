package ch.zhaw.jtodo.dao.hibernate;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
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
		Session session = sessionFactory.openSession();
		catDAO = new CategoryHibernateDAO();
		catDAO.setSession(session);
	}

	@Test
	public void writeCatalogToDBTest() {
		Category cat = new Category();
		
		cat.setName("test1");
		
		try {
			
			catDAO.write(cat);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Write to db failed");
		}
		Category resultCat = catDAO.findById(0);
		
		assertEquals(resultCat.getName(),cat.getName());
	}
	
	@Test
	public void findByIdTest(){
		Category resultCat = catDAO.findById(0);
		assertTrue(resultCat !=null);
	}
	
	@Test
	public void DeleteCategoryTest(){
		Category cat = new Category();
		cat.setId(0);
		try {
			catDAO.delete(cat);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Delete from db failed");
		}
		Category resultCat = catDAO.findById(0);
		assertTrue(resultCat == null);
	}
	
	@Test
	public void findAllTest(){
		
		for(int i = 0;i<5;i++){
			Category cat = new Category();
			cat.setName("test"+i);
			cat.setId(i);
			try {
				catDAO.write(cat);
				//Dirty hack, because the db needs a bit of time
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
				fail("Write to db failed");
			}
		}
		
		List<Category> catList = catDAO.findAll();
		
		assertTrue(catList.size()==5);
	}
	
	@Test
	public void findByCriterion(){
		
	}
	
	

}
