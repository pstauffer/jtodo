package ch.zhaw.jtodo.model;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import ch.zhaw.jtodo.dao.hibernate.CategoryHibernateDAO;
import ch.zhaw.jtodo.dao.hibernate.Preparation;

@RunWith(JMock.class)
public class DataHandlerTest {
	
	Mockery context = new JUnit4Mockery();
	private static DataHandler model;
	
	@BeforeClass
	public static void setUp() throws Exception {
		  // setup the session factory

	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
