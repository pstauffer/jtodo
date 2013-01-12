package ch.zhaw.jtodo.dao.hibernate;

import java.util.List;

import ch.zhaw.jtodo.dao.ITaskDAO;
import ch.zhaw.jtodo.domain.Category;
import ch.zhaw.jtodo.domain.Task;

/**
 * Konkrete Implementation für Hibernate des TaskDAO
 * @author yannik
 *
 */
public class TaskHibernateDAO extends GenericHibernateDAO<Task, Integer> implements  ITaskDAO{

	public TaskHibernateDAO() {
		super(Task.class);
	}

	@Override
	public List<Task> getTaskByCategory(Category category) {
		return null;
	}

}
