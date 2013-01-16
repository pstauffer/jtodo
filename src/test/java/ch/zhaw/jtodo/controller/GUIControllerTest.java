package ch.zhaw.jtodo.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Observable;
import java.util.Observer;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.zhaw.jtodo.domain.Category;
import ch.zhaw.jtodo.domain.Priority;
import ch.zhaw.jtodo.domain.Task;
import ch.zhaw.jtodo.model.IDataHandler;

public class GUIControllerTest {

	static Mockery context = new JUnit4Mockery();
	private static GUIController controller;
	
	static IDataHandler daoModelMockup = context.mock(IDataHandler.class);
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		controller = new GUIController(daoModelMockup,null);
		final TestClient testclient = new TestClient();
		context.checking(new Expectations() {{
			
			allowing(daoModelMockup).addNewObserver(testclient);
			allowing (daoModelMockup).getAllTasks();
			allowing(daoModelMockup).getAllPrioritys();
			allowing(daoModelMockup).getAllCategorys();
			allowing(daoModelMockup).getTaskByCategory(1);
			allowing(daoModelMockup).getTaskByCategory(2);
		    //oneOf (daoFactoryMockup).getTaskDAO().findAll();
		}});
		
		controller.addObserver(testclient);
	}
	
	@Test
	public void getInitalTest() {
		controller.getInitalData();
		
		assertTrue(TestClient.isTriggerd);
	}

	@Test
	public void getCategoryTest(){
		Category cat = new Category();
		cat.setId(1);
		cat.setName("Test cat");
		
		try{
			controller.getCategory(cat.getId());
		}catch(Exception e){
			fail("getCategorys failed");
		}
		
		assertTrue(TestClient.isTriggerd);
	}
	
	@Test
	public void getPriorityTest(){
		Priority prio = new Priority();
		prio.setId(1);
		prio.setName("Test prio");
		
		try{
			controller.getCategory(prio.getId());
		}catch(Exception e){
			fail("getPriorities failed");
		}
	}
	
	public static class TestClient implements Observer {
		
		private static TestClient instance;
		public static boolean isTriggerd = true;
		
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
