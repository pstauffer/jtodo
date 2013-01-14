package ch.zhaw.jtodo.model;

import java.util.List;
import java.util.Observer;

import ch.zhaw.jtodo.domain.Task;

public interface IDataHandler {

	public void createTask(Task task);

	public void getAllTasks();

	public Task getSpecificTask();
	
	public void getAllCategorys();
	
	public void getTaskByCategory(int id);
	
	public void updateTask(Task task);
	
	void addNewObserver(Observer view);
		

}
