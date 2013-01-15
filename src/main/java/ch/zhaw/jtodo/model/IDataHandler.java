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

	public void createTask(Task task);

	public void getAllTasks();

	public Task getSpecificTask();

	public void getAllCategorys();

	public void getAllPrioritys();

	public void getTaskByCategory(int id);

	public void updateTask(Task task);

	void addNewObserver(Observer view);

	public void getTaskByPriority(int prioID);

}
