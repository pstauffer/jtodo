package ch.zhaw.jtodo.dao.hibernate;

import java.sql.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

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
		
		List tasks = this.getSession().createCriteria(Task.class)
	    .add( Restrictions.eq("categoryid", category.getId()) )
	    .list();
		
		return tasks;
	}

}
