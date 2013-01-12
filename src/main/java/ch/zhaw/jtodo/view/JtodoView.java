package ch.zhaw.jtodo.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
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
import ch.zhaw.jtodo.dao.DAOFactory;
import ch.zhaw.jtodo.domain.Category;
import ch.zhaw.jtodo.domain.Task;
import ch.zhaw.jtodo.model.DataHandler;
import ch.zhaw.jtodo.model.IDataHandler;

public class JtodoView extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;
	private JTextField statusField;
	private JTextField newTaskTextField;
	private final JComboBox categoryBox;
	private JTable taskTable;
	private JList categoryList;
	private IDataHandler model;
	private IGUIController controller;
	private DefaultTableModel taskModel;

	public JtodoView(IDataHandler model) {
		this.model = model;
		controller = new GUIController(model,this);
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

		taskModel = new DefaultTableModel();
		taskTable = new JTable(taskModel);
		taskTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		DefaultComboBoxModel categoryBoxModel = new DefaultComboBoxModel();
		categoryBox = new JComboBox(categoryBoxModel);

		DefaultListModel categoryListModel = new DefaultListModel();
		categoryList = new JList(categoryListModel);

		taskModel.addColumn("Task");
		taskModel.addColumn("Description");
		taskModel.addColumn("Category");
		taskModel.addColumn("Priority");
		taskModel.addColumn("Date");
		taskModel.addColumn("Status");

		TableColumn col = taskTable.getColumnModel().getColumn(0);
		col.setPreferredWidth(200);
		col = taskTable.getColumnModel().getColumn(1);
		col.setPreferredWidth(200);
		col = taskTable.getColumnModel().getColumn(2);
		col.setPreferredWidth(80);
		col = taskTable.getColumnModel().getColumn(3);
		col.setPreferredWidth(80);
		col = taskTable.getColumnModel().getColumn(4);
		col.setPreferredWidth(80);
		col = taskTable.getColumnModel().getColumn(5);
		col.setPreferredWidth(40);

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
				taskTable.getModel());
		taskTable.setRowSorter(sorter);


		statusField = new JTextField(20);
		JButton addTaskButton = new JButton("Task adden");
		addTaskButton.setBounds(516, 5, 116, 29);
		// categoryBox.setBounds(17, 6, 172, 27);

		addTaskButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonAction(e);
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

		newTaskTextField = new JTextField(20);
		newTaskTextField.setBounds(262, 4, 254, 28);

		JTextField count = new JTextField(Integer.toString(taskTable
				.getRowCount()) + " Tasks");

		JPanel centerPanel = new JPanel();
		JPanel buttomPanel = new JPanel();
		TaskListPanel.add(centerPanel, BorderLayout.CENTER);
		TaskListPanel.add(buttomPanel, BorderLayout.SOUTH);
		centerPanel.add(taskTable);
		buttomPanel.add(count);

		SortFilterPanel.add(categoryList);
		NewTaskPanel.add(newTaskTextField);
		NewTaskPanel.add(categoryBox);
		NewTaskPanel.add(addTaskButton);
		ArchivPanel.add(statusField);

		JScrollPane scrollPaneTaskList = new JScrollPane(taskTable);
		scrollPaneTaskList
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneTaskList.setPreferredSize(new Dimension(400, 150));
		TaskListPanel.add(scrollPaneTaskList);

		frame.setSize(755, 344);
		frame.addWindowListener(new openWindowListener());
		
		//CHECK tis
		//controller.initDB(taskModel, categoryListModel, categoryBoxModel);
		frame.setVisible(true);
	}
	
	public void SetController(GUIController controller){
		this.controller = controller;
	}
	
	public void setModel(IDataHandler model){
		this.model = model;
	}

	private void buttonAction(ActionEvent e) {
		//Richtiger MVC ansatz, so m�ssen User Actions behandelt werden.
		//controller.handleButtonAction();
	}

	public void update() {
		// Use controller to do this
		Date now = new Date();
		Date mod = new Date();
		Task task = new Task(newTaskTextField.getText(), "description", 1, 1,
				now, 1, mod);

		DataHandler handler = new DataHandler(new DAOFactory());
		handler.createTask(task);
		statusField.setText("added the Task: " + this.getNewTask());

	}

	public String getNewTask() {
		return newTaskTextField.getText();
	}
	
	public void updateTaskList(List<Task> taskList) {

		List<Task> tasks = taskList;
		for (int i = 0; i < tasks.size(); i++) {
			{
				String taskName = tasks.get(i).getName();
				String taskDesc = tasks.get(i).getDescription();
				int taskCat = tasks.get(i).getCategoryid();
				int taskPrio = tasks.get(i).getPriorityid();
				Date taskDate = tasks.get(i).getDate();
				int taskStat = tasks.get(i).getStatus();
				// int taskID = tasks.get(i).getId();

				JCheckBox blubb = new JCheckBox();
				blubb.setBounds(99, 344, 28, 23);
				if (taskStat == 1) {
					blubb.setSelected(true);
				}

				taskModel.addRow(new Object[] { taskName, taskDesc, taskCat,
						taskPrio, taskDate, blubb });
			}
		}
	}

	@Override
	public void update(Observable arg0, Object element) {
		// Use this to update Tasks and Categorys
		Object listElement;
		if(element instanceof List){
			List list = (List)element;
			
			try{
				listElement = list.get(0);
			}catch(Exception e){
				return;
			}
			
			if(listElement instanceof Task){
				List<Task> taskList = list;
				updateTaskList(taskList);
			}
			
			if(listElement instanceof Category){
				//do something else
			}
		}
	}
	
	class openWindowListener extends WindowAdapter
	{
	  public void windowOpened(WindowEvent e)
	  {
		  controller.getInitalData();
	  }
	}

}

   
