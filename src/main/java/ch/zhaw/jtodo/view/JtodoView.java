package ch.zhaw.jtodo.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import ch.zhaw.jtodo.controller.GUIController;
import ch.zhaw.jtodo.model.JtodoModel;

public class JtodoView extends JFrame {
	private static final long serialVersionUID = 1L;
	private GUIController control;
	private JTextField text;
	private JTextField task;
	private final JComboBox category;

	public JtodoView(GUIController control) {
		super("JTodo -  PS & YK");

		this.control = control;
		task = new JTextField(20);

		// auswahl wird spaeter abgefuellt durch das auslesen der kategorien in
		// der db
		category = new JComboBox();
		category.insertItemAt("low", 0);
		category.insertItemAt("medium", 1);
		category.insertItemAt("high", 2);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewTask = new JMenuItem("New Task");
		mnNewMenu.add(mntmNewTask);

		JMenuItem mntmClose = new JMenuItem("Close");
		mnNewMenu.add(mntmClose);

		mntmClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(EXIT_ON_CLOSE);
			}
		});

		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);

		JMenuItem mntmHelp = new JMenuItem("Help");
		mnAbout.add(mntmHelp);

		JMenuItem mntmVersion = new JMenuItem("Version");
		mnAbout.add(mntmVersion);

		String[][] taskList = { { "Winterpneu montieren", "high" },
				{ "Einkaufen", "low" }, { "Ferien buchen", "medium" } };

		String[] columnNames = { "Task", "Priority" };
		text = new JTextField(20);

		JTable table = new JTable(taskList, columnNames);
		JScrollPane taskTable = new JScrollPane(table);
		JButton button = new JButton("Task adden");

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonAction(e);
			}
		});

		add(task, BorderLayout.LINE_START);
		add(text, BorderLayout.CENTER);
		add(category, BorderLayout.PAGE_START);
		add(taskTable, BorderLayout.PAGE_END);
		add(button, BorderLayout.LINE_END);

		pack();
		setVisible(true);
	}

	private void buttonAction(ActionEvent e) {
		control.handleButtonAction();
	}

	public void update(JtodoModel model) {
		if (model.getCounter() < 2) {
			text.setText("" + model.getCounter() + " Task added");
		} else {
			text.setText("" + model.getCounter() + " Tasks already added");
		}
		System.out.println(this.getTask());
	}

	public String getTask() {
		return task.getText();
	}

}
