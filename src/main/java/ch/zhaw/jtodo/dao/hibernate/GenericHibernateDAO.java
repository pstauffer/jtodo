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
 * Diese Klasse stellt generisch alle Methoden bereit die allgemein von
 * HibernateDAO's verwendet werden.
 * @param <T> spezifische Klasse
 * @param <ID> typ des PrimaryKeys
 */
public abstract class GenericHibernateDAO<T, ID extends Serializable>
		implements IGenericDAO<T, ID> {

	private Class<T> persistentClass;
	private SessionFactory factory;
	private Session session;
	
	/**
	 * Default Konstruktor, definiert den spezifischen Typ
	 * @param type
	 */
	public GenericHibernateDAO(Class<T> type) {
		this.persistentClass = type;
	}
	
	/**
	 * Session injection
	 * @param s Hibernate Session
	 */
    @SuppressWarnings("unchecked")
    public void setSession(Session s) {
        this.session = s;
    }
 
    /**
     * Returniert die aktuelle Hibernate Ession
     * @return aktuelle Hibernate Session
     */
    protected Session getSession() {
        if (session == null)
            throw new IllegalStateException("Session has not been set on DAO before usage");
        return session;
    }
	
	/**
	 * Gibt die typisierte Klasse des generischen DAOs zurück
	 * @return Klassenname
	 */
	public Class<T> getPersistentClass() {
		return persistentClass;
	}
	
	@SuppressWarnings("unchecked")
	public T findById(ID id) {
		T entity;
        try {
            entity = (T) session.get(this.getPersistentClass(), id);
            if (entity==null) {
                //log.debug("get successful  but no instance found");
            	return null;
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
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		Criteria criteria = session.createCriteria(this.persistentClass);
		List<T> returnList = criteria.list();
		
		return returnList;
	}
	
	@SuppressWarnings("unchecked")
	public T  write(T businessObject) throws Exception{
		 Transaction tx = null;
		 try {
		     tx = session.beginTransaction();
		     session.persist(businessObject);
		     tx.commit();
		     return businessObject;
		 }
		 catch (Exception e) {
		     if (tx!=null) tx.rollback();
		     throw e;
		 }
	}
	
	public void update(T businessObject) throws Exception{
		Transaction tx = null;
		try{
			tx= session.beginTransaction();
			session.update(businessObject);
			tx.commit();
		}catch(Exception e){
		     if (tx!=null) tx.rollback();
		     throw e;
		}
	}
	
	public void delete(T entity) throws Exception {
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
	}
	
	/**
	 * Sucht BO nach einem spezifisch definierten Kriterium
	 * @param criterion definiertes Kriterium
	 * @return Liste mit gefundenen BO's
	 */
	protected List<T> findByCriteria(Criterion criterion) {
		Criteria crit = session.createCriteria(getPersistentClass());
		crit.add(criterion);
		
		return crit.list();
	}

}
