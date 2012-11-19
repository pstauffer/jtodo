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

public class DAOFactory implements IDAOFactory{
    private SessionFactory factory;
    
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
    protected Session getCurrentSession() {
    	Session session = HibernateUtil.getSessionFactory().openSession();
        return session;
    }
 
 

}
