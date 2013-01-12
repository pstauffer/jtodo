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

	@Override
	public void addObserver(Observer view) {
		model.addNewObserver(view);
	}

	@Override
	public void getInitalData() {
		model.getAllTasks();
		model.getAllCategorys();
	}

	@Override
	public void addTaskButtonAction() {
		System.out.println("haha");
		Date now = new Date();
		Date mod = new Date();
		// Task task = new Task(newTaskTextField.getText(), "description", 1, 1,
		// now, 1, mod);

		Task task = new Task("haha", "description", 1, 1, now, 1, mod);

		DataHandler handler = new DataHandler(new DAOFactory());
		handler.createTask(task);
		// statusField.setText("added the Task: " + this.getNewTask());
		// model.getTaskList();
		// view.update(model);

	}

}
