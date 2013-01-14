package ch.zhaw.jtodo.dao;

import java.util.List;

import ch.zhaw.jtodo.domain.Category;
import ch.zhaw.jtodo.domain.Priority;
import ch.zhaw.jtodo.domain.Task;

/**
 * Dieses Interface erweitert das generische Interface @IGenericDAO mit
 * spezifischen Methoden für das TaskDAO.
 * 
 * @author yannik
 */
public interface ITaskDAO extends IGenericDAO<Task, Integer> {

	public List<Task> getTaskByCategory(Category category);

	public List<Task> getTaskByPriority(Priority prio);

}
