package ch.zhaw.jtodo.model;

import java.util.Observer;

import ch.zhaw.jtodo.domain.Task;

/**
 * 
 * MVC => Model Interface für die Model Klasse DataHandler
 * 
 * @author pascal
 */
public interface IDataHandler {
	
	/**
	 * Erstellt einen neuen Task
	 * @param task der erstellt werden soll
	 */
	void createTask(Task task);

	/**
	 * Notifizert alle Observer mit den aktuellen Tasks vom Datenmodel
	 */
	void getAllTasks();

	/**
	 * Notifizert alle Observer mit den aktuellen Categorys vom Datenmodel
	 */
	void getAllCategorys();

	/**
	 * Notifizert alle Observer mit den Prioritys Tasks vom Datenmodel
	 */
	void getAllPrioritys();

	/**
	 * Notifizert alle Observer mit den Task zu der gegebenen ID
	 * @param catID ID der gewünschten Category
	 */
	void getTaskByCategory(int catID);

	/**
	 * Aktualisiert die Daten eines Tasks
	 * @param task aktualisierter Task
	 */
	void updateTask(Task task);

	/**
	 * Fügt einen neuen Observer hinzu der notifiert wird
	 * @param view
	 */
	void addNewObserver(Observer view);

	/**
	 * Notifizert alle Observer mit den Task zu der gegebenen ID
	 * @param prioID ID der gewünschten Priorität
	 */
	public void getTaskByPriority(int prioID);

}
