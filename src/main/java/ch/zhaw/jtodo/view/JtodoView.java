package ch.zhaw.jtodo.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import ch.zhaw.jtodo.controller.GUIController;
import ch.zhaw.jtodo.dao.DAOFactory;
import ch.zhaw.jtodo.domain.Category;
import ch.zhaw.jtodo.domain.Task;
import ch.zhaw.jtodo.model.DataHandler;
import ch.zhaw.jtodo.model.JtodoModel;

public class JtodoView extends JFrame {
	private static final long serialVersionUID = 1L;
	private GUIController control;
	private JTextField text;
	private JTextField newTask;
	private final JComboBox category;
	private JTable taskList;

	public JtodoView(GUIController control) {
		JFrame frame = new JFrame("JTodo -  PS & YK");
		frame.getContentPane().setLayout(new BorderLayout());

		this.control = control;
		category = new JComboBox();

		DataHandler handler = new DataHandler(new DAOFactory());

		List<Category> cat = handler.getAllCategorys();
		for (int i = 0; i < cat.size(); i++) {
			category.insertItemAt(cat.get(i).getName(), i);
		}

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

		List<Task> tasks = handler.getAllTasks();

		String task[][] = {};
		String desc[] = { "Task", "Description" };
		DefaultTableModel model = new DefaultTableModel(task, desc);
		taskList = new JTable(model);

		for (int i = 0; i < tasks.size(); i++) {
			model.insertRow(taskList.getRowCount(), new Object[] {
					tasks.get(i).getName(), tasks.get(i).getDescription() });
		}

		Container c = frame.getContentPane();

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		JLabel label = new JLabel("Test");
		tabbedPane.addTab("New tab", null, label, null);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel topPanel = new JPanel();
		panel_1.add(topPanel, BorderLayout.NORTH);

		JPanel buttomPanel = new JPanel();
		panel_1.add(buttomPanel, BorderLayout.SOUTH);

		JPanel panel = new JPanel();
		buttomPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		newTask = new JTextField(20);
		newTask.setBounds(262, 4, 254, 28);
		taskList.setBounds(17, 44, 615, 177);
		taskList.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		text = new JTextField(20);
		JButton button = new JButton("Task adden");
		button.setBounds(516, 5, 116, 29);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonAction(e);
			}
		});

		JPanel mainPanel = new JPanel();
		panel_1.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);
		mainPanel.add(newTask);
		mainPanel.add(text);
		mainPanel.add(button);
		mainPanel.add(taskList);
		category.setBounds(17, 6, 172, 27);
		mainPanel.add(category);

		JPanel leftPanel = new JPanel();
		panel_1.add(leftPanel, BorderLayout.WEST);
		leftPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		JLabel lblBlubb = new JLabel("blubb");
		leftPanel.add(lblBlubb);

		frame.setSize(755, 344);
		frame.setVisible(true);
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
