package ch.zhaw.jtodo.model;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import org.hibernate.Session;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import ch.zhaw.jtodo.dao.DAOFactory;
import ch.zhaw.jtodo.dao.IDAOFactory;
import ch.zhaw.jtodo.dao.hibernate.CategoryHibernateDAO;
import ch.zhaw.jtodo.dao.hibernate.Preparation;
import ch.zhaw.jtodo.dao.hibernate.TaskHibernateDAO;
import ch.zhaw.jtodo.domain.Category;
import ch.zhaw.jtodo.domain.Priority;
import ch.zhaw.jtodo.domain.Task;

@RunWith(JMock.class)
public class DataHandlerTest {
	
	static Mockery context = new JUnit4Mockery();
	private static DataHandler model;
	//private static TestClient testclient;
	static DataHandlerTest instance;
	
	static IDAOFactory daoFactoryMockup = context.mock(IDAOFactory.class);
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		instance = new DataHandlerTest();
		model = new DataHandler(daoFactoryMockup);
		TestClient testclient = new TestClient();
		model.addNewObserver(testclient);
		
		context.checking(new Expectations() {{
			
			allowing(daoFactoryMockup).getTaskDAO().findAll();
			allowing (daoFactoryMockup).getPriorityDAO().findAll();
			allowing (daoFactoryMockup).getCategoryDAO().findAll();
			
		    //oneOf (daoFactoryMockup).getTaskDAO().findAll();
		}});
	}
	
	@Test
	public void getAllTasksTest() {
		

		
		//will(returnValue(new Task("Test", "Test Task", 1,
		//2, new Date(), 1, new Date())));
		
		model.getAllTasks();
	
		assertTrue("getAllTasks() Method should notify observer", TestClient.isTriggerd);
		TestClient.isTriggerd = false;
	}
	
	@Test
	public void getAllCategorysTest(){
		
		model.getAllCategorys();
		
		assertTrue("getAllCategorys() ",TestClient.isTriggerd);
		TestClient.isTriggerd = false;
	}
	
	@Test
	public void getAllPrioritysTest(){
		
		model.getAllPrioritys();
		
		assertTrue("getAllPrioritys() ",TestClient.isTriggerd);
		TestClient.isTriggerd = false;
	}
	
	public void getTaskByCategoryTest(){
		
		final Category testCat = new Category(1,"Test Category");
		
		context.checking(new Expectations() {{
			allowing (daoFactoryMockup).getTaskDAO().getTaskByCategory(testCat);
		}});
		
		model.getTaskByCategory(testCat.getId());
		
		assertTrue("getAllPrioritys() ",TestClient.isTriggerd);
		TestClient.isTriggerd = false;
	}
	
	public void getTaskByPriorityTest(){
		final Priority testPrio = new Priority(1,"Test Category");
		
		context.checking(new Expectations() {{
			
			allowing (daoFactoryMockup).getPriorityDAO().findById(testPrio.getId());
			will(returnValue(testPrio));
		}});
		
		model.getTaskByPriority(testPrio.getId());
		
		assertTrue("getAllPrioritys() ",TestClient.isTriggerd);
		TestClient.isTriggerd = false;
	}
	
	@Test
	public void updateTaskTest(){
		
	}
	
	
	public static class TestClient implements Observer {
		
		private static TestClient instance;
		public static boolean isTriggerd;
		
		public static TestClient getInstance(){
			if(instance==null){
				instance = new TestClient();
			}
			return instance;
		}

		@Override
		public void update(Observable arg0, Object arg1) {
			// TODO Auto-generated method stub
			isTriggerd = true;
		}
		
    }
}

