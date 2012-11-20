package ch.zhaw.jtodo.dao.hibernate;

import ch.zhaw.jtodo.dao.ITaskDAO;
import ch.zhaw.jtodo.domain.Task;

/**
 * Konkrete Implementation f�r Hibernate des TaskDAO
 * @author yannik
 *
 */
public class TaskHibernateDAO extends GenericHibernateDAO<Task, Integer> implements  ITaskDAO{

	public TaskHibernateDAO() {
		super(Task.class);
	}

}
