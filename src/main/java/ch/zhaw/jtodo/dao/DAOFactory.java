package ch.zhaw.jtodo.dao;

import ch.zhaw.jtodo.dao.hibernate.CategoryHibernateDAO;
import ch.zhaw.jtodo.dao.hibernate.HibernateUtil;
import ch.zhaw.jtodo.dao.hibernate.PriorityHibernateDAO;
import ch.zhaw.jtodo.dao.hibernate.ReminderHibernateDAO;
import ch.zhaw.jtodo.dao.hibernate.TaskHibernateDAO;

/**
 * Konkrete Factory Implementation definiert durch Interface @IDAOFactory
 * Verteilt die einzelnen instanzen der Data Access Objects
 * 
 * @author yannik
 */
public class DAOFactory implements IDAOFactory {
	
	public ICategoryDAO getCategoryDAO() {
		CategoryHibernateDAO dao = new CategoryHibernateDAO();
		dao.setSession(HibernateUtil.getSession());
		return (dao);
	}

	public IReminderDAO getReminderDAO() {
		ReminderHibernateDAO dao = new ReminderHibernateDAO();
		dao.setSession(HibernateUtil.getSession());
		return (dao);
	}

	public IPriorityDAO getPriorityDAO() {
		PriorityHibernateDAO dao = new PriorityHibernateDAO();
		dao.setSession(HibernateUtil.getSession());
		return (dao);
	}

	public ITaskDAO getTaskDAO() {
		TaskHibernateDAO dao = new TaskHibernateDAO();
		dao.setSession(HibernateUtil.getSession());
		return (dao);
	}
}
