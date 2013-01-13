package ch.zhaw.jtodo.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import ch.zhaw.jtodo.controller.GUIController;
import ch.zhaw.jtodo.controller.IGUIController;
import ch.zhaw.jtodo.domain.Category;
import ch.zhaw.jtodo.domain.Task;
import ch.zhaw.jtodo.model.IDataHandler;

import com.toedter.calendar.JSpinnerDateEditor;

public class JtodoView extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;
	private JTextField statusField;
	private JTextField newTaskNameField;
	private JTextField newTaskDescriptionField;
	private JTable taskTable;
	private JTable categoryTable;
	private JComboBox categoryBox;
	private JComboBox prioBox;
	private IDataHandler model;
	private IGUIController controller;
	private DefaultTableModel taskModel;
	private DefaultTableModel categoryTableModel;
	private DefaultComboBoxModel categoryBoxModel;
	private DefaultComboBoxModel prioBoxModel;
	private JTextField taskCount;
	private JSpinnerDateEditor dateChooser;

	public JtodoView(IDataHandler model) {
		this.model = model;
		controller = new GUIController(model, this);
		controller.addObserver(this);

		JFrame frame = new JFrame("JTodo -  PS & YK");
		frame.getContentPane().setLayout(new BorderLayout());

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

		taskCount = new JTextField();
		taskCount.setText("keine Tasks");

		categoryBoxModel = new DefaultComboBoxModel();
		categoryBox = new JComboBox(categoryBoxModel);
		String emptyCat = " ";
		categoryBox.addItem(emptyCat);

		prioBoxModel = new DefaultComboBoxModel();
		prioBox = new JComboBox(prioBoxModel);
		String prio = "prio1";
		prioBox.addItem(prio);

		categoryTableModel = new DefaultTableModel();
		categoryTable = new JTable(categoryTableModel);
		categoryTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		categoryTableModel.addColumn("ID");
		categoryTableModel.addColumn("Category");

		TableColumn catCol = categoryTable.getColumnModel().getColumn(0);
		catCol.setPreferredWidth(40);
		catCol = categoryTable.getColumnModel().getColumn(1);
		catCol.setPreferredWidth(200);

		TableRowSorter<TableModel> catSorter = new TableRowSorter<TableModel>(
				categoryTable.getModel());
		categoryTable.setRowSorter(catSorter);

		categoryTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = categoryTable.getSelectedRow();
				int selectedID = (Integer) categoryTable.getValueAt(row, 0);
				System.out.println(selectedID);
			}
		});

		taskModel = new DefaultTableModel();
		taskTable = new JTable(taskModel);
		taskTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		taskModel.addColumn("ID");
		taskModel.addColumn("Task");
		taskModel.addColumn("Description");
		taskModel.addColumn("Category");
		taskModel.addColumn("Priority");
		taskModel.addColumn("Date");
		taskModel.addColumn("Status");

		TableColumn taskCol = taskTable.getColumnModel().getColumn(0);
		taskCol.setPreferredWidth(40);
		taskCol = taskTable.getColumnModel().getColumn(1);
		taskCol.setPreferredWidth(200);
		taskCol = taskTable.getColumnModel().getColumn(2);
		taskCol.setPreferredWidth(200);
		taskCol = taskTable.getColumnModel().getColumn(3);
		taskCol.setPreferredWidth(80);
		taskCol = taskTable.getColumnModel().getColumn(4);
		taskCol.setPreferredWidth(80);
		taskCol = taskTable.getColumnModel().getColumn(5);
		taskCol.setPreferredWidth(80);
		taskCol = taskTable.getColumnModel().getColumn(6);
		taskCol.setPreferredWidth(40);

		TableRowSorter<TableModel> taskSorter = new TableRowSorter<TableModel>(
				taskTable.getModel());
		taskTable.setRowSorter(taskSorter);

		taskTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = taskTable.getSelectedRow();
				int selectedID = (Integer) taskTable.getValueAt(row, 0);
				System.out.println(selectedID);
			}
		});

		JButton addTaskButton = new JButton("Task adden");

		addTaskButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addTaskButtonAction(e);
			}
		});

		JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabs, BorderLayout.CENTER);

		JPanel TaskListPanel = new JPanel();
		TaskListPanel.setLayout(new BorderLayout(0, 0));

		JPanel NewTaskPanel = new JPanel();
		NewTaskPanel.setLayout(new BorderLayout(0, 0));

		JPanel SortFilterPanel = new JPanel();
		SortFilterPanel.setLayout(new BorderLayout(0, 0));

		JPanel ArchivPanel = new JPanel();
		ArchivPanel.setLayout(new BorderLayout(0, 0));

		tabs.addTab("Task Liste", null, TaskListPanel, null);
		tabs.addTab("New Task", null, NewTaskPanel, null);
		tabs.addTab("Sort / Filter", null, SortFilterPanel, null);
		tabs.addTab("Archiv", null, ArchivPanel, null);

		newTaskNameField = new JTextField(20);
		newTaskDescriptionField = new JTextField(20);
		statusField = new JTextField(20);

		JPanel taskListCenterPanel = new JPanel();
		JPanel taskListButtomPanel = new JPanel();
		TaskListPanel.add(taskListCenterPanel, BorderLayout.CENTER);
		TaskListPanel.add(taskListButtomPanel, BorderLayout.SOUTH);
		taskListCenterPanel.add(taskTable);
		taskListButtomPanel.add(taskCount);

		JPanel sortFilterCenterPanel = new JPanel();
		SortFilterPanel.add(sortFilterCenterPanel, BorderLayout.CENTER);
		sortFilterCenterPanel.add(categoryTable);

		JPanel newTaskCenterPanel = new JPanel();
		JPanel newTaskButtomPanel = new JPanel();
		NewTaskPanel.add(newTaskCenterPanel, BorderLayout.CENTER);
		NewTaskPanel.add(newTaskButtomPanel, BorderLayout.SOUTH);
		newTaskCenterPanel.add(newTaskNameField);
		newTaskCenterPanel.add(newTaskDescriptionField);
		newTaskCenterPanel.add(categoryBox);
		newTaskCenterPanel.add(prioBox);
		newTaskButtomPanel.add(addTaskButton);

		JPanel archivCenterPanel = new JPanel();
		JPanel archivButtomPanel = new JPanel();
		ArchivPanel.add(archivCenterPanel, BorderLayout.CENTER);
		ArchivPanel.add(archivButtomPanel, BorderLayout.SOUTH);

		dateChooser = new JSpinnerDateEditor();
		Date date = new Date();
		dateChooser.setDate(date);
		dateChooser.setDateFormatString("dd/MM/yyyy");
		archivCenterPanel.add(dateChooser);

		archivButtomPanel.add(statusField);

		JScrollPane scrollPaneTaskList = new JScrollPane(taskTable);
		scrollPaneTaskList
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneTaskList.setPreferredSize(new Dimension(400, 150));
		TaskListPanel.add(scrollPaneTaskList);

		JScrollPane scrollPaneCatList = new JScrollPane(categoryTable);
		scrollPaneCatList
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneCatList.setPreferredSize(new Dimension(400, 150));
		SortFilterPanel.add(scrollPaneCatList);

		frame.setSize(755, 344);
		frame.addWindowListener(new openWindowListener());

		frame.setVisible(true);
	}

	public void SetController(GUIController controller) {
		this.controller = controller;
	}

	public void setModel(IDataHandler model) {
		this.model = model;
	}

	private void addTaskButtonAction(ActionEvent e) {
		// Richtiger MVC ansatz, so müssen User Actions behandelt werden.
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateChooser.getDate());
		System.out.println(calendar.get(Calendar.YEAR));
		System.out.println(calendar.get(Calendar.MONTH));
		System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

		controller.addTaskButtonAction();
	}

	public String getNewTask() {
		return newTaskNameField.getText();
	}

	public void updateCategoryList(List<Category> categoryList) {

		for (int i = 0; i < categoryList.size(); i++) {
			{
				int categoryID = categoryList.get(i).getId();
				String categoryName = categoryList.get(i).getName();

				categoryTableModel.addRow(new Object[] { categoryID,
						categoryName });
				categoryBoxModel.addElement(categoryName);
			}
		}
	}

	public void updateTaskList(List<Task> taskList) {

		for (int i = 0; i < taskList.size(); i++) {
			{
				int taskID = taskList.get(i).getId();
				String taskName = taskList.get(i).getName();
				String taskDesc = taskList.get(i).getDescription();
				int taskCat = taskList.get(i).getCategoryid();
				int taskPrio = taskList.get(i).getPriorityid();
				Date taskDate = taskList.get(i).getDate();
				int taskStat = taskList.get(i).getStatus();

				taskModel.addRow(new Object[] { taskID, taskName, taskDesc,
						taskCat, taskPrio, taskDate, taskStat });
			}

			taskCount.setText(taskList.size() + " Tasks");
		}
	}

	@Override
	public void update(Observable arg0, Object element) {
		// Use this to update Tasks and Categorys
		Object listElement;
		if (element instanceof List) {
			List list = (List) element;

			try {
				listElement = list.get(0);
			} catch (Exception e) {
				return;
			}

			if (listElement instanceof Task) {
				List<Task> taskList = list;
				updateTaskList(taskList);
			}

			if (listElement instanceof Category) {
				List<Category> categoryList = list;
				updateCategoryList(categoryList);
			}
		}
	}

	class openWindowListener extends WindowAdapter {
		public void windowOpened(WindowEvent e) {
			controller.getInitalData();
		}
	}

}
