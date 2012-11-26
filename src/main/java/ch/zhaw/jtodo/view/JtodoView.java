package ch.zhaw.jtodo.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import ch.zhaw.jtodo.controller.GUIController;
import ch.zhaw.jtodo.model.JtodoModel;

public class JtodoView extends JFrame {
	private GUIController control;
	private JButton button;
	private JTextField text;
	private JTextField task;
	private final JComboBox category;

	public JtodoView(GUIController control) {
		super("JTodo");
		this.control = control;
		button = new JButton("Task adden");
		text = new JTextField(20);
		task = new JTextField(20);
		category = new JComboBox();
		// auswahl wird spaeter abgefuellt durch das auslesen der kategorien in
		// der db
		category.insertItemAt("low", 0);
		category.insertItemAt("medium", 1);
		category.insertItemAt("high", 2);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonAction(e);
			}
		});
		setLayout(new FlowLayout());
		add(task);
		add(category);
		add(text);
		add(button);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	private void buttonAction(ActionEvent e) {
		control.handleButtonAction();
	}

	public void update(JtodoModel model) {
		text.setText("" + model.getCounter() + " Task bereits hinzugefuegt");
		System.out.println(this.getTask());
	}

	public String getTask() {
		return task.getText();
	}

}
