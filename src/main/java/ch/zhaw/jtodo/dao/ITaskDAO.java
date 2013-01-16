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
	
	/**
	 * Spezifische TaskDAO Methode um alle Tasks einer Kategorie zu finden
	 * @param category gesuchte Kategorie
	 * @return Liste mit allen gefundenen Tasks
	 */
	public List<Task> getTaskByCategory(Category category);

	/**
	 * Spezifische TaskDAO Method um alle Tasks einer Priorität zu finden
	 * @param prio gesuchte Prioritäts
	 * @return Liste mit allen gefunden Prioritäten
	 */
	public List<Task> getTaskByPriority(Priority prio);

}
