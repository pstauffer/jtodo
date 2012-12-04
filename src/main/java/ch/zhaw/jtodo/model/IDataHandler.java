package ch.zhaw.jtodo.model;

import java.util.List;

import ch.zhaw.jtodo.domain.Task;

public interface IDataHandler {

	public void createTask(Task task);

	public List<Task> getAllTasks();

	public Task getSpecificTask();

}
