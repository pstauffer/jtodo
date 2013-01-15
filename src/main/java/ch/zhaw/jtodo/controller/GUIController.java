package ch.zhaw.jtodo.controller;

import java.util.Observer;

import ch.zhaw.jtodo.domain.Task;
import ch.zhaw.jtodo.model.IDataHandler;
import ch.zhaw.jtodo.view.JtodoView;

/**
 * 
 * MVC => Controller Klasse für das GUI
 * 
 * @author pascal
 */
public class GUIController implements IGUIController {

	private IDataHandler model;
	private JtodoView view;

	public GUIController(IDataHandler model, JtodoView view) {
		this.model = model;
		this.view = view;
	}

	/**
	 * fügt dem model die view hinzu
	 */
	public void addObserver(Observer view) {
		model.addNewObserver(view);
	}

	/**
	 * gibt mir die Tasks anhand der categoryID zurück
	 */
	@Override
	public void getCategory(int catID) {
		if (catID == 0) {
			model.getAllTasks();
		} else {
			model.getTaskByCategory(catID);
		}
	}

	/**
	 * gibt mir die Tasks anhand der priorityID zurück
	 */
	@Override
	public void getPriority(int prioID) {
		if (prioID == 0) {
			model.getAllTasks();
		} else {
			model.getTaskByPriority(prioID);
		}
	}

	/**
	 * lädt mir die benötigten Daten beim ersten Aufruf
	 */
	public void getInitalData() {
		model.getAllPrioritys();
		model.getAllCategorys();
		model.getAllTasks();
	}

	/**
	 * fügt mir einen neuen Task hinzu
	 */
	@Override
	public void addTask(Task task) {
		model.createTask(task);
		// notify
		model.getAllTasks();
	}

	/**
	 * updatet ein Task Object
	 */
	@Override
	public void update(Task task) {
		model.updateTask(task);
	}
}
