package ch.zhaw.jtodo.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

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
	private final JComboBox categoryBox;
	private JTable taskTable;
	private JList categoryList;

	public JtodoView(GUIController control) {
		JFrame frame = new JFrame("JTodo -  PS & YK");
		frame.getContentPane().setLayout(new BorderLayout());
		this.control = control;

		DefaultComboBoxModel categoryBoxModel = new DefaultComboBoxModel();
		categoryBox = new JComboBox(categoryBoxModel);

		DefaultListModel categoryListModel = new DefaultListModel();
		categoryList = new JList(categoryListModel);
		categoryList.setFixedCellWidth(40);
		categoryList.setFixedCellHeight(40);
		categoryListModel.setSize(40);

		// DataHandler handler = new DataHandler(new DAOFactory());

		// List<Category> cat = handler.getAllCategorys();
		// for (int i = 0; i < cat.size(); i++) {
		// String categoryName = cat.get(i).getName();
		// categoryBoxModel.addElement(categoryName);
		// categoryListModel.addElement(categoryName);
		// }

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		JMenuItem newTaskMenuItem = new JMenuItem("New Task");
		fileMenu.add(newTaskMenuItem);
		JMenuItem closeMenuItem = new JMenuItem("Close");
		fileMenu.add(closeMenuItem);
		closeMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(EXIT_ON_CLOSE);
			}
		});

		JMenu aboutMenu = new JMenu("About");
		menuBar.add(aboutMenu);
		JMenuItem helpMenuItem = new JMenuItem("Help");
		aboutMenu.add(helpMenuItem);
		JMenuItem versionMenuItem = new JMenuItem("Version");
		aboutMenu.add(versionMenuItem);

		DefaultTableModel taskModel = new DefaultTableModel();
		taskTable = new JTable(taskModel);
		taskTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		taskModel.addColumn("Task");
		taskModel.addColumn("Description");
		TableColumn col = taskTable.getColumnModel().getColumn(0);
		col.setPreferredWidth(40);
		col = taskTable.getColumnModel().getColumn(1);
		col.setPreferredWidth(120);

		// List<Task> tasks = handler.getAllTasks();
		// for (int i = 0; i < tasks.size(); i++) {
		// {
		// String taskName = tasks.get(i).getName();
		// String taskDesc = tasks.get(i).getDescription();
		// taskModel.addRow(new Object[] { taskName, taskDesc });
		// }
		// }

		JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabs, BorderLayout.CENTER);

		JPanel mainTabPanel = new JPanel();
		tabs.addTab("Main", null, mainTabPanel, null);
		mainTabPanel.setLayout(new BorderLayout(0, 0));

		JLabel label = new JLabel("Test Tab - Description...");
		tabs.addTab("Test Tab", null, label, null);

		JPanel topPanel = new JPanel();
		mainTabPanel.add(topPanel, BorderLayout.NORTH);

		JPanel buttomPanel = new JPanel();
		mainTabPanel.add(buttomPanel, BorderLayout.SOUTH);

		JPanel panel = new JPanel();
		buttomPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		newTask = new JTextField(20);
		newTask.setBounds(262, 4, 254, 28);
		taskTable.setBounds(17, 44, 615, 156);

		text = new JTextField(20);
		JButton button = new JButton("Task adden");
		button.setBounds(516, 5, 116, 29);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonAction(e);
			}
		});

		JPanel centerPanel = new JPanel();
		mainTabPanel.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(null);
		centerPanel.add(newTask);
		buttomPanel.add(text);
		centerPanel.add(button);
		centerPanel.add(taskTable);
		categoryBox.setBounds(17, 6, 172, 27);
		centerPanel.add(categoryBox);

		JPanel leftPanel = new JPanel();
		leftPanel.add(categoryList);

		mainTabPanel.add(leftPanel, BorderLayout.WEST);

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
		text.setText("added the Task: " + this.getNewTask());

	}

	public String getNewTask() {
		return newTask.getText();
	}

}
