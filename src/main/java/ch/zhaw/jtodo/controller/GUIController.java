package ch.zhaw.jtodo.controller;

import java.util.Observer;

import ch.zhaw.jtodo.domain.Task;
import ch.zhaw.jtodo.model.IDataHandler;
import ch.zhaw.jtodo.view.JtodoView;

/**
 * 
 * MVC => Controller Klasse für das GUI, gibt dem GUI die möglichkeit
 * auf das Model zuzugreifen
 * 
 * @author pascal
 */
public class GUIController implements IGUIController {

	private IDataHandler model;
	private JtodoView view;
	
	/**
	 * Konstruktor für den Kontroller, implementiert das mvc
	 * @param model 
	 * @param view
	 */
	public GUIController(IDataHandler model, JtodoView view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void addObserver(Observer view) {
		model.addNewObserver(view);
	}

	@Override
	public void getCategory(int catID) {
		if (catID == 0) {
			model.getAllTasks();
		} else {
			model.getTaskByCategory(catID);
		}
	}

	@Override
	public void getPriority(int prioID) {
		if (prioID == 0) {
			model.getAllTasks();
		} else {
			model.getTaskByPriority(prioID);
		}
	}
	
	@Override
	public void getInitalData() {
		model.getAllPrioritys();
		model.getAllCategorys();
		model.getAllTasks();
	}
	
	@Override
	public void addTask(Task task) {
		model.createTask(task);
		// notify
		model.getAllTasks();
	}

	@Override
	public void update(Task task) {
		model.updateTask(task);
	}
}
