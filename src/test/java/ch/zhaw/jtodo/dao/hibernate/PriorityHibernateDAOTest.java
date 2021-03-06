package ch.zhaw.jtodo.dao.hibernate;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.zhaw.jtodo.domain.Category;
import ch.zhaw.jtodo.domain.Priority;
import ch.zhaw.jtodo.domain.Task;

public class PriorityHibernateDAOTest {
	
	private static PriorityHibernateDAO prioDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Session session = Preparation.getSessionFactory().openSession();
		prioDAO = new PriorityHibernateDAO();
		prioDAO.setSession(session);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		prioDAO.getSession().close();
		Preparation.closeSessionFactory();
	}

	@Test
	public void readWritePriorityToDBTest() {
		Priority prio = new Priority();
		prio.setName("fubar");
		prio.setId(200);
		
		try {
			prioDAO.write(prio);
		} catch (Exception e) {
			e.printStackTrace();
			fail("db write failed");
		}
		
		Priority resultPrio = prioDAO.findById(200);
		
		assertEquals(resultPrio.getName(),prio.getName());
	}
	
	@Test
	public void deleteTaskTest(){
		
		Priority prio = new Priority();
		
		prio.setName("high");
		prio.setId(201);
		
		try {
			prioDAO.write(prio);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Write to db failed");
		}
		
		try {
			prioDAO.delete(prio);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Delete from db failed");
		}
		Priority resultPrio = prioDAO.findById(201);
		assertTrue(resultPrio == null);
	}
	
	@Test
	public void findAllTest(){
		
		for(int i = 0;i<5;i++){
			Priority prio = new Priority();
			prio.setName("Prio"+i);
			prio.setId(i);
			try {
				prioDAO.write(prio);
				//Dirty hack, because the db needs a bit of time
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
				fail("Write to db failed");
			}
		}
		
		List<Priority> prioList = prioDAO.findAll();
		
		assertTrue(prioList.size()>=5);
	}
	
	@Test
	public void findByCriterion(){
		Priority prio = new Priority();
		prio.setName("fubar");
		prio.setId(220);
		try {
			prioDAO.write(prio);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Write to db failed");
		}
		
		Criteria cr = prioDAO.getSession().createCriteria(Priority.class);
		cr.add(Restrictions.like("name", "Prio%"));
		List<Priority> results = cr.list();
		
		assertTrue(results.size()==5);
	}
}
