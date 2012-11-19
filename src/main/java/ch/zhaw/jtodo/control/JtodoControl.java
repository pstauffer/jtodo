package ch.zhaw.jtodo.control;

import ch.zhaw.jtodo.model.JtodoModel;
import ch.zhaw.jtodo.view.JtodoView;

public class JtodoControl {

	private JtodoModel model;
	private JtodoView view;

	public JtodoControl() {
		model = new JtodoModel();
		view = new JtodoView(this);
	}

	public void handleButtonAction() {
		System.out.println("Taste wuerde gedrueckt");
		model.countUp();
		view.update(model);
	}
}
