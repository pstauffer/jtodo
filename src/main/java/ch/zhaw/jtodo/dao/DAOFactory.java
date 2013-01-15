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
	

	/**
	 * Returns ICategoryDAO
	 */
	public ICategoryDAO getCategoryDAO() {
		CategoryHibernateDAO dao = new CategoryHibernateDAO();
		dao.setSession(HibernateUtil.getSession());
		return (dao);
	}

	/**
	 * Returns IReminderDAO
	 */
	public IReminderDAO getReminderDAO() {
		ReminderHibernateDAO dao = new ReminderHibernateDAO();
		dao.setSession(HibernateUtil.getSession());
		return (dao);
	}

	/**
	 * Returns IPriorityDAO
	 */
	public IPriorityDAO getPriorityDAO() {
		PriorityHibernateDAO dao = new PriorityHibernateDAO();
		dao.setSession(HibernateUtil.getSession());
		return (dao);
	}

	/**
	 * Returns ITaskDAO
	 */
	public ITaskDAO getTaskDAO() {
		TaskHibernateDAO dao = new TaskHibernateDAO();
		dao.setSession(HibernateUtil.getSession());
		return (dao);
	}
}
