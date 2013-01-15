package ch.zhaw.jtodo.model;

import java.util.Observer;

import ch.zhaw.jtodo.domain.Task;

public interface IDataHandler {

	public void createTask(Task task);

	public void getAllTasks();

	public void getAllCategorys();

	public void getAllPrioritys();

	public void getTaskByCategory(int catID);

	public void updateTask(Task task);

	void addNewObserver(Observer view);

	public void getTaskByPriority(int prioID);

}
