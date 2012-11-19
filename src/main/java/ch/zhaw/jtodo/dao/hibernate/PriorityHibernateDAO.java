package ch.zhaw.jtodo.dao.hibernate;

import ch.zhaw.jtodo.dao.IPriorityDAO;
import ch.zhaw.jtodo.domain.Priority;

public class PriorityHibernateDAO extends GenericHibernateDAO<Priority, Integer> implements  IPriorityDAO {

	public PriorityHibernateDAO() {
		super(Priority.class);
	}

}
