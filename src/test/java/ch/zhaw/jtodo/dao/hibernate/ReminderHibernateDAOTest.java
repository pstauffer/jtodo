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

import ch.zhaw.jtodo.domain.Priority;
import ch.zhaw.jtodo.domain.Reminder;

public class ReminderHibernateDAOTest {

	private static ReminderHibernateDAO reminderDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Session session = Preparation.getSessionFactory().openSession();
		reminderDAO = new ReminderHibernateDAO();
		reminderDAO.setSession(session);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		reminderDAO.getSession().close();
		Preparation.closeSessionFactory();
	}
	
	@Test
	public void readWritePriorityToDBTest() {
		Reminder reminder = new Reminder();
		reminder.setTaskid(1);
		reminder.setDate(new Date());
		reminder.setId(200);
		
		try {
			reminderDAO.write(reminder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("db write failed");
		}
		
		Reminder resultReminder = reminderDAO.findById(200);
		
		assertEquals(resultReminder.getDate(),resultReminder.getDate());
	}
	
	@Test
	public void deleteTaskTest(){
		
		Reminder reminder = new Reminder();
		
		reminder.setDate(new Date());
		reminder.setId(201);
		
		try {
			reminderDAO.write(reminder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Write to db failed");
		}
		
		try {
			reminderDAO.delete(reminder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Delete from db failed");
		}
		Reminder resultReminder = reminderDAO.findById(201);
		assertTrue(resultReminder == null);
	}
	
	@Test
	public void findAllTest(){
		
		for(int i = 0;i<5;i++){
			Reminder reminder = new Reminder();
			reminder.setDate(new Date());
			reminder.setId(i);
			try {
				reminderDAO.write(reminder);
				//Dirty hack, because the db needs a bit of time
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
				fail("Write to db failed");
			}
		}
		
		List<Reminder> reminderList = reminderDAO.findAll();
		
		assertTrue(reminderList.size()>=5);
	}
	
	@Test
	public void findByCriterion(){
		Reminder reminder = new Reminder();
		reminder.setDate(new Date());
		reminder.setId(220);
		try {
			reminderDAO.write(reminder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Write to db failed");
		}
		
		Criteria cr = reminderDAO.getSession().createCriteria(Reminder.class);
		//cr.add(Restrictions.like("Date", "%"));
		//List<Priority> results = cr.list();
		//TODO: Find a way to add dates to criteria
		//assertTrue(results.size()==5);
	}
	

}
