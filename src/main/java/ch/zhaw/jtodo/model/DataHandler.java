package ch.zhaw.jtodo.model;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import ch.zhaw.jtodo.dao.IDAOFactory;
import ch.zhaw.jtodo.domain.Category;
import ch.zhaw.jtodo.domain.Priority;
import ch.zhaw.jtodo.domain.Task;

public class DataHandler extends Observable implements IDataHandler {

	private IDAOFactory daoFactory;

	public DataHandler(IDAOFactory factory) {
		this.daoFactory = factory;
	}

	@Override
	public void createTask(Task task) {
		try {
			daoFactory.getTaskDAO().write(task);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void getAllTasks() {
		List<Task> taskList = daoFactory.getTaskDAO().findAll();
		notify(taskList);
	}

	private void notify(Object element) {
		this.setChanged();
		this.notifyObservers(element);
	}

	@Override
	public void getAllCategorys() {
		List<Category> categoryList = daoFactory.getCategoryDAO().findAll();
		notify(categoryList);
	}

	@Override
	public void getAllPrioritys() {
		List<Priority> priorityList = daoFactory.getPriorityDAO().findAll();
		notify(priorityList);
	}

	@Override
	public void addNewObserver(Observer view) {
		this.addObserver(view);
	}

	@Override
	public void getTaskByCategory(int id) {
		Category cat = daoFactory.getCategoryDAO().findById(id);

		if (cat == null) {
			return;
		}

		List<Task> taskList = daoFactory.getTaskDAO().getTaskByCategory(cat);
		notify(taskList);
	}

	@Override
	public void getTaskByPriority(int id) {
		Priority prio = daoFactory.getPriorityDAO().findById(id);

		if (prio == null) {
			return;
		}

		List<Task> taskList = daoFactory.getTaskDAO().getTaskByPriority(prio);
		notify(taskList);
	}

	@Override
	public void updateTask(Task task) {

		try {
			daoFactory.getTaskDAO().update(task);
		} catch (Exception e) {
			// Exception will be thrown if task id not found
		}
		// notfiy all observers that a task has changed
		this.getAllTasks();

	}

}
