package ch.zhaw.jtodo.dao;

import java.util.List;

import ch.zhaw.jtodo.domain.Category;
import ch.zhaw.jtodo.domain.Priority;
import ch.zhaw.jtodo.domain.Task;

/**
 * Dieses Interface erweitert das generische Interface @IGenericDAO mit
 * spezifischen Methoden f�r das TaskDAO.
 * 
 * @author yannik
 */
public interface ITaskDAO extends IGenericDAO<Task, Integer> {
	
	/**
	 * Spezifische TaskDAO Methode um alle Tasks einer Kategorie zu finden
	 * @param category gesuchte Kategorie
	 * @return Liste mit allen gefundenen Tasks
	 */
	public List<Task> getTaskByCategory(Category category);

	/**
	 * Spezifische TaskDAO Method um alle Tasks einer Priorit�t zu finden
	 * @param prio gesuchte Priorit�ts
	 * @return Liste mit allen gefunden Priorit�ten
	 */
	public List<Task> getTaskByPriority(Priority prio);

}
