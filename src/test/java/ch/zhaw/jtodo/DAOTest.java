package ch.zhaw.jtodo;

import static org.junit.Assert.*;

import org.hibernate.SessionFactory;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.api.Expectation;
import org.jmock.integration.junit4.JMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ch.zhaw.jtodo.dao.DAOFactory;
import ch.zhaw.jtodo.dao.ICategoryDAO;
import ch.zhaw.jtodo.dao.IPriorityDAO;
import ch.zhaw.jtodo.dao.IReminderDAO;
import ch.zhaw.jtodo.dao.ITaskDAO;

@RunWith(JMock.class)
public class DAOTest {
	
	Mockery context = new Mockery();
	SessionFactory factory;
	
    @Before
    public void init() {
    	factory = context.mock(SessionFactory.class);
    }
    
    @After
    public void clear() {
        
    }
	
	@Test
	public void CategoryDAOtest() {
		DAOFactory factory2 = new DAOFactory(factory);
		
		ICategoryDAO catDAO = factory2.getCategoryDAO();
		
		context.checking(new Expectations() {{

	    }});
		
		assertTrue(true);
	}
	
	@Test
	public void PriorityDAOtest() {
		DAOFactory factory2 = new DAOFactory(factory);
		
		IPriorityDAO prioDAO = factory2.getPriorityDAO();
		
		context.checking(new Expectations() {{
	        
	    }});
		
		assertTrue(true);
	}
	
	@Test
	public void ReminderDAOtest() {
		DAOFactory factory2 = new DAOFactory(factory);
		
		IReminderDAO prioDAO = factory2.getReminderDAO();
		
		context.checking(new Expectations() {{
	       
	    }});
		
		assertTrue(true);
	}
	
	@Test
	public void TaskDAOtest() {
		DAOFactory factory2 = new DAOFactory(factory);
		
		ITaskDAO prioDAO = factory2.getTaskDAO();
		
		context.checking(new Expectations() {{
	       
	    }});
		
		assertTrue(true);
	}

}
