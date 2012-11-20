package ch.zhaw.jtodo.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import ch.zhaw.jtodo.controller.GUIController;
import ch.zhaw.jtodo.model.JtodoModel;

public class JtodoView extends JFrame {
	private GUIController control;
	private JButton button;
	private JTextField text;
	private JTextField task;

	public JtodoView(GUIController control) {
		super("JTodo");
		this.control = control;
		button = new JButton("Task adden");
		text = new JTextField(20);
		task = new JTextField(20);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonAction(e);
			}
		});
		setLayout(new FlowLayout());
		add(button);
		add(task);
		add(text);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	private void buttonAction(ActionEvent e) {
		control.handleButtonAction();
	}

	public void update(JtodoModel model) {
		text.setText("" + model.getCounter() + " Task bereits hinzugefuegt");
		System.out.println(task.getText());
	}

}
