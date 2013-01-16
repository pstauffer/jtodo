package ch.zhaw.jtodo.dao.hibernate;

import ch.zhaw.jtodo.dao.IPriorityDAO;
import ch.zhaw.jtodo.domain.Priority;

/**
 * Konkrete Implementation für Hibernate des PriorityDAO
 * @author yannik
 *
 */
public class PriorityHibernateDAO extends GenericHibernateDAO<Priority, Integer> implements  IPriorityDAO {

	/**
	 * Default Konstruktor, ruft super Klasse mit BO Klassentyp auf
	 */
	public PriorityHibernateDAO() {
		super(Priority.class);
	}

}
