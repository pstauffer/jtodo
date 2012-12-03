package ch.zhaw.jtodo.dao.hibernate;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.zhaw.jtodo.domain.Priority;

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
	public void test() {
		Priority prio = new Priority();
		prio.setName("fubar");
		
		try {
			prioDAO.write(prio);
		} catch (Exception e) {
			e.printStackTrace();
			fail("db write failed");
		}
	}

}
