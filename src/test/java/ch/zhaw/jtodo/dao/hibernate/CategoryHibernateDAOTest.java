package ch.zhaw.jtodo.dao.hibernate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.zhaw.jtodo.domain.Category;

public class CategoryHibernateDAOTest {

	private static CategoryHibernateDAO catDAO;
	
	@BeforeClass
	public static void setUp() throws Exception {
		  // setup the session factory
		Session session = Preparation.getSessionFactory().openSession();
		catDAO = new CategoryHibernateDAO();
		catDAO.setSession(session);
	}
	
	@AfterClass
	public static void tearDown(){
		catDAO.getSession().close();
		Preparation.closeSessionFactory();
	}

	@Test
	public void readWriteCategoryTest() {
		Category cat = new Category();
		
		cat.setName("write");
		cat.setId(200);
		
		try {
			
			catDAO.write(cat);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Write to db failed");
		}
		
		Category resultCat = catDAO.findById(200);
		assertEquals(resultCat.getName(),cat.getName());
	}
	
	
	@Test
	public void DeleteCategoryTest(){
		Category cat = new Category();
		
		cat.setName("delete");
		cat.setId(201);
		
		try {
			catDAO.write(cat);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Write to db failed");
		}
		
		try {
			catDAO.delete(cat);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Delete from db failed");
		}
		Category resultCat = catDAO.findById(201);
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
		
		assertTrue(catList.size()>=5);
	}
	
	@Test
	public void findByCriterion(){
		Category cat = new Category();
		cat.setName("fubar");
		cat.setId(5);
		try {
			catDAO.write(cat);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Write to db failed");
		}
		
		Criteria cr = catDAO.getSession().createCriteria(Category.class);
		cr.add(Restrictions.like("name", "test%"));
		List<Category> results = cr.list();
		
		assertTrue(results.size()==5);
	}
	
	

}
