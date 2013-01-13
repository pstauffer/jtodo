package ch.zhaw.jtodo.controller;

import java.util.Date;
import java.util.Observer;

import ch.zhaw.jtodo.dao.DAOFactory;
import ch.zhaw.jtodo.domain.Task;
import ch.zhaw.jtodo.model.DataHandler;
import ch.zhaw.jtodo.model.IDataHandler;
import ch.zhaw.jtodo.view.JtodoView;

public class GUIController implements IGUIController {

	private IDataHandler model;
	private JtodoView view;

	public GUIController(IDataHandler model, JtodoView view) {
		this.model = model;
		this.view = view;
	}

	public void addObserver(Observer view) {
		model.addNewObserver(view);
	}

	public void getCategory(int catID) {
		model.getTaskByCategory(catID);
	}

	public void getInitalData() {
		model.getAllTasks();
		model.getAllCategorys();
	}

	@Override
	public void addTaskButtonAction(String taskName, String taskDescription,
			int catID, Date date) {
		Date mod = new Date();

		Task task = new Task(taskName, taskDescription, catID, 1, date, 1, mod);

		DataHandler handler = new DataHandler(new DAOFactory());
		handler.createTask(task);
		// notify
		model.getAllTasks();
	}

}
