package ch.zhaw.jtodo.controller;

import ch.zhaw.jtodo.model.JtodoModel;
import ch.zhaw.jtodo.view.JtodoView;

public class GUIController {

	private JtodoModel model;
	private JtodoView view;

	public GUIController() {
		model = new JtodoModel();
		view = new JtodoView(this);
	}

	public void handleButtonAction() {
		// spaeter wird hier zusätzlich der insert in die db hinzugefuegt
		// System.out.println("Task wuerde hinzugefuegt");
		model.countUp();
		// diese methode wird spaeter benoetigt, um die taskliste an die view
		// weiterzugeben.
		model.getTaskList();
		view.update(model);
	}
}
