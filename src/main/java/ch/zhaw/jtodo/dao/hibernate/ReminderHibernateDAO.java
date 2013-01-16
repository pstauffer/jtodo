package ch.zhaw.jtodo.dao.hibernate;

import ch.zhaw.jtodo.dao.IReminderDAO;
import ch.zhaw.jtodo.domain.Category;
import ch.zhaw.jtodo.domain.Reminder;

/**
 * Konkrete Implementation für Hibernate des ReminderDAO
 * @author yannik
 *
 */
public class ReminderHibernateDAO extends GenericHibernateDAO<Reminder, Integer> implements  IReminderDAO{
	
	/**
	 * Default Konstruktor, ruft super Klasse mit BO Klassentyp auf
	 */
	public ReminderHibernateDAO() {
		super(Reminder.class);
	}
}
