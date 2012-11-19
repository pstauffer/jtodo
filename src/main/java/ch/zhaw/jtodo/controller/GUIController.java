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
		System.out.println("Taste wuerde gedrueckt");
		model.countUp();
		view.update(model);
	}
}
