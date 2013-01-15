package ch.zhaw.jtodo.model;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import ch.zhaw.jtodo.dao.IDAOFactory;
import ch.zhaw.jtodo.domain.Category;
import ch.zhaw.jtodo.domain.Priority;
import ch.zhaw.jtodo.domain.Task;

/**
 * 
 * MVC => Model Klasse
 * 
 * @author pascal
 */
public class DataHandler extends Observable implements IDataHandler {

	private IDAOFactory daoFactory;

	public DataHandler(IDAOFactory factory) {
		this.daoFactory = factory;
	}

	/**
	 * erstellt das neue Task-Object in der Datenbank
	 */
	@Override
	public void createTask(Task task) {
		try {
			daoFactory.getTaskDAO().write(task);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * gibt mir die taskList aus der Datenbank zurück und notifiziert via
	 * Obserable
	 */
	@Override
	public void getAllTasks() {
		List<Task> taskList = daoFactory.getTaskDAO().findAll();
		notify(taskList);

	}

	/**
	 * gibt mir die categoryList aus der Datenbank zurück und notifiziert via
	 * Obserable
	 */
	@Override
	public void getAllCategorys() {
		List<Category> categoryList = daoFactory.getCategoryDAO().findAll();
		notify(categoryList);
	}

	/**
	 * gibt mir die priorityList aus der Datenbank zurück und notifiziert via
	 * Obserable
	 */
	@Override
	public void getAllPrioritys() {
		List<Priority> priorityList = daoFactory.getPriorityDAO().findAll();
		notify(priorityList);
	}

	/**
	 * zur Zeit nicht aktiv
	 */
	@Override
	public Task getSpecificTask() {
		return null;
	}

	/**
	 * notifiziert ein Object, welches geändert wurde
	 */
	private void notify(Object element) {
		this.setChanged();
		this.notifyObservers(element);
	}

	/**
	 * fügt den Observer zu
	 */
	@Override
	public void addNewObserver(Observer view) {
		this.addObserver(view);
	}

	/**
	 * liest einen Task anhand der categoryID aus der Datenbank aus und
	 * updatet&notifizert die taskList
	 */
	@Override
	public void getTaskByCategory(int id) {
		Category cat = daoFactory.getCategoryDAO().findById(id);

		if (cat == null) {
			return;
		}

		List<Task> taskList = daoFactory.getTaskDAO().getTaskByCategory(cat);
		notify(taskList);
	}

	/**
	 * liest einen Task anhand der priorityID aus der Datenbank aus und
	 * updatet&notifizert die taskList
	 */
	@Override
	public void getTaskByPriority(int id) {
		Priority prio = daoFactory.getPriorityDAO().findById(id);

		if (prio == null) {
			return;
		}

		List<Task> taskList = daoFactory.getTaskDAO().getTaskByPriority(prio);
		notify(taskList);
	}

	/**
	 * updatet ein Taks Object in der Datenbank und notifiziert
	 */
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
