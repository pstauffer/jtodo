package ch.zhaw.jtodo.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import ch.zhaw.jtodo.controller.GUIController;
import ch.zhaw.jtodo.dao.DAOFactory;
import ch.zhaw.jtodo.domain.Task;
import ch.zhaw.jtodo.model.DataHandler;
import ch.zhaw.jtodo.model.JtodoModel;

public class JtodoView extends JFrame {
	private static final long serialVersionUID = 1L;
	private GUIController control;
	private JTextField text;
	private JTextField newTask;
	private final JComboBox category;

	public JtodoView(GUIController control) {
		super("JTodo -  PS & YK");

		this.control = control;
		newTask = new JTextField(20);

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

		DataHandler handler = new DataHandler(new DAOFactory());
		List<Task> tasks = handler.getAllTasks();

		String task[][] = {};
		String desc[] = { "Task", "Description" };
		DefaultTableModel model = new DefaultTableModel(task, desc);
		JTable taskList = new JTable(model);
		taskList.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		for (int i = 0; i < tasks.size(); i++) {
			model.insertRow(taskList.getRowCount(), new Object[] {
					tasks.get(i).getName(), tasks.get(i).getDescription() });
		}

		text = new JTextField(20);
		JButton button = new JButton("Task adden");

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonAction(e);
			}
		});

		getContentPane().add(category, BorderLayout.PAGE_START);
		getContentPane().add(newTask, BorderLayout.LINE_START);
		getContentPane().add(text, BorderLayout.CENTER);
		getContentPane().add(button, BorderLayout.LINE_END);
		getContentPane().add(taskList, BorderLayout.PAGE_END);

		pack();
		setVisible(true);
	}

	private void buttonAction(ActionEvent e) {
		control.handleButtonAction();
	}

	public void update(JtodoModel model) {
		Date now = new Date();
		Date mod = new Date();
		Task task = new Task(newTask.getText(), "description", 1, 1, now, 1,
				mod);

		DataHandler handler = new DataHandler(new DAOFactory());
		handler.createTask(task);

		if (model.getCounter() < 2) {
			text.setText("" + model.getCounter() + " Task added");
		} else {
			text.setText("" + model.getCounter() + " Tasks already added");
		}
		System.out.println(this.getTask());
	}

	public String getTask() {
		return newTask.getText();
	}

}
