package ch.zhaw.jtodo.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;

import ch.zhaw.jtodo.dao.IGenericDAO;
import ch.zhaw.jtodo.domain.Category;

public abstract class GenericHibernateDAO<T, ID extends Serializable>
		implements IGenericDAO<T, ID> {

	private Class<T> persistentClass;
	private SessionFactory factory;

	public GenericHibernateDAO(Class<T> type) {
		this.persistentClass = type;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	@SuppressWarnings("unchecked")
	public T findById(ID id) {
		T entity;
		Session session = factory.openSession();
        try {
            entity = (T) session.get(this.getPersistentClass(), id);
            if (entity==null) {
                //log.debug("get successful  but no instance found");
            }
            else {
                //log.debug("get successful, instance found");
            }
            return entity;
        }
        catch (RuntimeException re) {
            //log.error("get failed", re);
            throw re;
        }
        finally{
        	session.close();
        }
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(this.persistentClass);
		session.close();
		
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public T  write(T businessObject) throws Exception{
		 Session session = factory.openSession();
		 Transaction tx = null;
		 try {
		     tx = session.beginTransaction();
		     session.saveOrUpdate(businessObject);
		     tx.commit();
		     return businessObject;
		 }
		 catch (Exception e) {
		     if (tx!=null) tx.rollback();
		     throw e;
		 }
		 finally {
		     session.close();
		 }
	}

	public void delete(T entity) throws Exception {
		 Session session = factory.openSession();
		 Transaction tx = null;
		 try {
		     tx = session.beginTransaction();
		     session.delete(entity);
		     tx.commit();
		 }
		 catch (Exception e) {
		     if (tx!=null) tx.rollback();
		     throw e;
		 }
		 finally {
		     session.close();
		 }
	}
	
	protected List<T> findByCriteria(Criterion criterion) {
		Session session = factory.openSession();
		Criteria crit = session.createCriteria(getPersistentClass());
		crit.add(criterion);
		
		session.close();
		return crit.list();
	}

}
