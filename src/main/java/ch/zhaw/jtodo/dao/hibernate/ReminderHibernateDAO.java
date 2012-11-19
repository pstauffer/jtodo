package ch.zhaw.jtodo.dao.hibernate;

import ch.zhaw.jtodo.dao.IReminderDAO;
import ch.zhaw.jtodo.domain.Category;
import ch.zhaw.jtodo.domain.Reminder;

public class ReminderHibernateDAO extends GenericHibernateDAO<Reminder, Integer> implements  IReminderDAO{
	
	public ReminderHibernateDAO() {
		super(Reminder.class);
	}
}
