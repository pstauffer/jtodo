package ch.zhaw.jtodo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ch.zhaw.jtodo.dao.hibernate.CategoryHibernateDAO;
import ch.zhaw.jtodo.dao.hibernate.HibernateUtil;
import ch.zhaw.jtodo.dao.hibernate.PriorityHibernateDAO;
import ch.zhaw.jtodo.dao.hibernate.ReminderHibernateDAO;
import ch.zhaw.jtodo.dao.hibernate.TaskHibernateDAO;
import ch.zhaw.jtodo.domain.Category;
import ch.zhaw.jtodo.domain.Priority;

/**
 * Konkrete Factory Implementation definiert durch Interface @IDAOFactory
 * Verteilt die einzelnen instanzen der Data Access Objects
 * @author yannik
 */
public class DAOFactory implements IDAOFactory{
    private SessionFactory factory;
    
    /**
     * Konstruktor der DAOFactory, diese muss mit einer hibernate sessionFactory
     * initalisiert werde
     * @param sessionFactory hibernate session factory
     */
    public DAOFactory(SessionFactory sessionFactory){
    	factory = sessionFactory;	
    }
    
	public ICategoryDAO getCategoryDAO() {
		CategoryHibernateDAO dao = new CategoryHibernateDAO();
		dao.setFactory(this.factory);
		return (dao);
	}
	
	public IReminderDAO getReminderDAO() {
		ReminderHibernateDAO dao = new ReminderHibernateDAO();
		dao.setFactory(this.factory);
		return (dao);
	}
	
	public IPriorityDAO getPriorityDAO() {
		PriorityHibernateDAO dao = new PriorityHibernateDAO();
		dao.setFactory(this.factory);
		return (dao);
	}
	public ITaskDAO getTaskDAO() {
		TaskHibernateDAO dao = new TaskHibernateDAO();
		dao.setFactory(this.factory);
		return (dao);
	}
}
