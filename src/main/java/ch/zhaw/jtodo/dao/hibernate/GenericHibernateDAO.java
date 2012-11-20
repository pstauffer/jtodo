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

/**
 * 
 * @author yannik
 *
 * @param <T>
 * @param <ID>
 */
public abstract class GenericHibernateDAO<T, ID extends Serializable>
		implements IGenericDAO<T, ID> {

	private Class<T> persistentClass;
	private SessionFactory factory;
	
	/**
	 * 
	 * @param type
	 */
	public GenericHibernateDAO(Class<T> type) {
		this.persistentClass = type;
	}

	/**
	 * 
	 * @param factory
	 */
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
	/**
	 * 
	 * @return
	 */
	public Class<T> getPersistentClass() {
		return persistentClass;
	}
	
	/**
	 * Findet ein BusinessObject mithilfe seiner eindeutigen ID
	 */
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

	/**
	 * Finds all businessObject of type on the db and returns them in a list
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(this.persistentClass);
		List<T> returnList = criteria.list();
		session.close();
		
		return returnList;
	}
	
	/**
	 * Schreibt ein BusinessObject persistent auf die Datenbank, dies geschieht
	 * konkret �ber Hibernate 
	 */
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
	
	/**
	 * L�scht ein BusinessObject auf der DB, daf�r wird die Hibernate SessionFactory
	 * verwendet um eine neue Session zu erzeugen
	 */
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
	
	/**
	 * 
	 * @param criterion
	 * @return
	 */
	protected List<T> findByCriteria(Criterion criterion) {
		Session session = factory.openSession();
		Criteria crit = session.createCriteria(getPersistentClass());
		crit.add(criterion);
		
		session.close();
		return crit.list();
	}

}
