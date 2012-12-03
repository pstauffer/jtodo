package ch.zhaw.jtodo.dao.hibernate;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.zhaw.jtodo.domain.Category;
import ch.zhaw.jtodo.domain.Task;

public class TaskHibernateDAOTest {

	private static TaskHibernateDAO taskDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Session session = Preparation.getSessionFactory().openSession();
		taskDAO = new TaskHibernateDAO();
		taskDAO.setSession(session);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		taskDAO.getSession().close();
		Preparation.closeSessionFactory();
	}

	@Test
	public void readWriteTaskToDBTest() {
		
		Date date = new Date();
		
		Task task = new Task();
		
		task.setName("write");
		task.setDescription("write");
		task.setDate(date);
		task.setModifiydate(date);
		//task.setPriorityid(0);
		//task.setStatus(0);
		task.setId(200);
		
		try {
			
			taskDAO.write(task);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Write to db failed");
		}
		Task resultTask = taskDAO.findById(200);
		
		assertEquals(task.getName(),resultTask.getName());
	}
	
	@Test
	public void DeleteTaskTest(){
		Date date = new Date();
		
		Task task = new Task();
		
		task.setName("delete");
		task.setDescription("delete");
		task.setDate(date);
		task.setModifiydate(date);
		//task.setPriorityid(0);
		//task.setStatus(0);
		task.setId(201);
		try {
			taskDAO.write(task);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Write to db failed");
		}
		
		try {
			taskDAO.delete(task);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Delete from db failed");
		}
		Task resulTask = taskDAO.findById(201);
		assertTrue(resulTask == null);
	}
	
	@Test
	public void findAllTest(){
		
		for(int i = 0;i<5;i++){
			Task task = new Task();
			task.setName("test"+i);
			task.setDescription("Description"+i);
			task.setId(i);
			try {
				taskDAO.write(task);
				//Dirty hack, because the db needs a bit of time
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
				fail("Write to db failed");
			}
		}
		
		List<Task> taskList = taskDAO.findAll();
		
		assertTrue(taskList.size()>=5);
	}
	
	@Test
	public void findByCriterion(){
		Task task = new Task();
		task.setName("fubar");
		task.setDescription("fubar");
		task.setId(5);
		try {
			taskDAO.write(task);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Write to db failed");
		}
		
		Criteria cr = taskDAO.getSession().createCriteria(Task.class);
		cr.add(Restrictions.like("description", "Description%"));
		List<Category> results = cr.list();
		
		assertTrue(results.size()==5);
	}
	
	

}
