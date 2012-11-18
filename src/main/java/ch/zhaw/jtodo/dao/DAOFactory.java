package ch.zhaw.jtodo.dao;

import org.hibernate.Session;

import ch.zhaw.jtodo.dao.hibernate.CategoryDAOHibernate;
import ch.zhaw.jtodo.dao.hibernate.HibernateUtil;
import ch.zhaw.jtodo.domain.Category;
import ch.zhaw.jtodo.domain.Priority;

public class DAOFactory implements IDAOFactory{
    private static DAOFactory daoFactory;
    static{
           daoFactory = new DAOFactory();
    }
    
    //Define the factory method, return the instance of specific classes
    public static DAOFactory getInstance(){
           return daoFactory;
    }
	
    
	public ICategoryDAO getCategoryDAO() {
		CategoryDAOHibernate dao = new CategoryDAOHibernate();
		dao.setFactory(HibernateUtil.getSessionFactory());
		return (dao);
	}

    protected Session getCurrentSession() {
    	Session session = HibernateUtil.getSessionFactory().openSession();
        return session;
    }
 
 

}
