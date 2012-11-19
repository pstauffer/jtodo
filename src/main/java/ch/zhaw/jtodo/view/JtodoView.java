package ch.zhaw.jtodo.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import ch.zhaw.jtodo.control.JtodoControl;
import ch.zhaw.jtodo.model.JtodoModel;

public class JtodoView extends JFrame {
	private JtodoControl control;
	private JButton button;
	private JTextField text;

	public JtodoView(JtodoControl control) {
		super("MVC Test");
		this.control = control;
		button = new JButton("klick");
		text = new JTextField(20);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonAction(e);
			}
		});
		setLayout(new FlowLayout());
		add(button);
		add(text);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	private void buttonAction(ActionEvent e) {
		control.handleButtonAction();
	}

	public void update(JtodoModel model) {
		text.setText("Button wurde " + model.getCounter() + " mal gedrueckt");
	}
}
