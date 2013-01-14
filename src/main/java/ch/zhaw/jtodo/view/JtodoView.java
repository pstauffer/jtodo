package ch.zhaw.jtodo.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import ch.zhaw.jtodo.controller.GUIController;
import ch.zhaw.jtodo.controller.IGUIController;
import ch.zhaw.jtodo.domain.Category;
import ch.zhaw.jtodo.domain.Priority;
import ch.zhaw.jtodo.domain.Task;
import ch.zhaw.jtodo.model.IDataHandler;

import com.toedter.calendar.JSpinnerDateEditor;

public class JtodoView extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;
	private JTextField newTaskNameField;
	private JTextField newTaskDescriptionField;
	private JLabel newTaskNameLabel;
	private JLabel newTaskDescpritionLabel;
	private JLabel newTaskCategoryLabel;
	private JLabel newTaskPriorityLabel;
	private JLabel newTaskDateLabel;
	private JTable taskTable;
	private JTable categoryTable;
	private JTable priorityTable;
	private JTable archivTable;
	private JComboBox categoryBox;
	private JComboBox prioBox;
	private IDataHandler model;
	private IGUIController controller;
	private DefaultTableModel categoryTableModel;
	private DefaultTableModel priorityTableModel;
	private DefaultComboBoxModel categoryBoxModel;
	private DefaultComboBoxModel prioBoxModel;
	private JTextField taskCount;
	private JTextField archivCount;
	private JSpinnerDateEditor dateChooser;
	private JtodoTableModel jtodoTableModelTask;
	private JtodoTableModel jtodoTableModelArchiv;
	private JTabbedPane tabs;
	private JPanel NewTaskPanel;
	private JPanel TaskListPanel;
	private JPanel AboutPanel;

	public JtodoView(IDataHandler model) {
		this.model = model;
		controller = new GUIController(model, this);
		controller.addObserver(this);

		JFrame frame = new JFrame("JTodo -  PS & YK");
		frame.getContentPane().setLayout(new BorderLayout());

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu fileMenu = new JMenu("Menu");
		menuBar.add(fileMenu);
		JMenuItem taskListMenuItem = new JMenuItem("Tasklist");
		fileMenu.add(taskListMenuItem);
		JMenuItem newTaskMenuItem = new JMenuItem("New Task");
		fileMenu.add(newTaskMenuItem);
		JMenuItem closeMenuItem = new JMenuItem("Close");
		fileMenu.add(closeMenuItem);

		closeMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int eingabe = JOptionPane.showConfirmDialog(null,
						"JToDo wirklick schliessen?", "Close?",
						JOptionPane.YES_NO_OPTION);

				if (eingabe == JOptionPane.YES_OPTION) {
					System.exit(EXIT_ON_CLOSE);
				}

			}
		});

		newTaskMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabs.setSelectedComponent(NewTaskPanel);
			}
		});

		taskListMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabs.setSelectedComponent(TaskListPanel);
			}
		});

		JMenu aboutMenu = new JMenu("About");
		menuBar.add(aboutMenu);
		JMenuItem wikiMenuItem = new JMenuItem("Wiki");
		aboutMenu.add(wikiMenuItem);
		JMenuItem githubMenuItem = new JMenuItem("Github");
		aboutMenu.add(githubMenuItem);

		wikiMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabs.setSelectedComponent(AboutPanel);
			}
		});

		githubMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabs.setSelectedComponent(AboutPanel);
			}
		});

		taskCount = new JTextField();
		taskCount.setText("keine Tasks");

		archivCount = new JTextField();
		archivCount.setText("keine abgeschlossenen Tasks");

		categoryBoxModel = new DefaultComboBoxModel();
		categoryBox = new JComboBox(categoryBoxModel);
		String emptyCat = " ";
		categoryBox.addItem(emptyCat);

		prioBoxModel = new DefaultComboBoxModel();
		prioBox = new JComboBox(prioBoxModel);
		String emptyPrio = " ";
		prioBox.addItem(emptyPrio);

		categoryTableModel = new DefaultTableModel();
		categoryTable = new JTable(categoryTableModel);
		categoryTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		categoryTableModel.addColumn("Category");
		categoryTableModel.addRow(new Object[] { "Alle" });

		TableColumn catCol = categoryTable.getColumnModel().getColumn(0);
		catCol.setPreferredWidth(100);

		TableRowSorter<TableModel> catSorter = new TableRowSorter<TableModel>(
				categoryTable.getModel());
		categoryTable.setRowSorter(catSorter);

		categoryTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = categoryTable.getSelectedRow();
				if (row == -1) {
					return;
				}
				int count = priorityTable.getRowCount();
				priorityTable.removeRowSelectionInterval(0, count - 1);
				if (row == 0) {
					int selectedID = 0;
					controller.getCategory(selectedID);
				} else {
					Category cat = (Category) categoryTable.getValueAt(row, 0);
					int selectedID = cat.getId();
					controller.getCategory(selectedID);
				}
			}
		});

		priorityTableModel = new DefaultTableModel();
		priorityTable = new JTable(priorityTableModel);
		priorityTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		priorityTableModel.addColumn("Priority");
		priorityTableModel.addRow(new Object[] { "Alle" });

		TableColumn prioCol = priorityTable.getColumnModel().getColumn(0);
		prioCol.setPreferredWidth(100);

		TableRowSorter<TableModel> prioSorter = new TableRowSorter<TableModel>(
				priorityTable.getModel());
		priorityTable.setRowSorter(prioSorter);

		priorityTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = priorityTable.getSelectedRow();
				if (row == -1) {
					return;
				}
				int count = categoryTable.getRowCount();
				categoryTable.removeRowSelectionInterval(0, count - 1);
				if (row == 0) {
					int selectedID = 0;
					controller.getPriority(selectedID);
				} else {
					Priority prio = (Priority) priorityTable.getValueAt(row, 0);
					int selectedID = prio.getId();
					controller.getPriority(selectedID);
				}
			}
		});

		jtodoTableModelTask = new JtodoTableModel();
		taskTable = new JTable(jtodoTableModelTask);
		taskTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		jtodoTableModelTask.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				int row = e.getFirstRow();
				if (e.getColumn() == 5 || e.getColumn() == 0
						|| e.getColumn() == 1) {
					Task task = jtodoTableModelTask.getValueAtRow(row);
					controller.update(task);
				}
			}
		});

		TableColumn taskCol = taskTable.getColumnModel().getColumn(0);
		taskCol.setPreferredWidth(150);
		taskCol = taskTable.getColumnModel().getColumn(1);
		taskCol.setPreferredWidth(200);
		taskCol = taskTable.getColumnModel().getColumn(2);
		taskCol.setPreferredWidth(80);
		taskCol = taskTable.getColumnModel().getColumn(3);
		taskCol.setPreferredWidth(60);
		taskCol = taskTable.getColumnModel().getColumn(4);
		taskCol.setPreferredWidth(100);
		taskCol = taskTable.getColumnModel().getColumn(5);
		taskCol.setPreferredWidth(40);

		TableRowSorter<TableModel> taskSorter = new TableRowSorter<TableModel>(
				taskTable.getModel());
		taskTable.setRowSorter(taskSorter);

		jtodoTableModelArchiv = new JtodoTableModel();
		archivTable = new JTable(jtodoTableModelArchiv);
		archivTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		jtodoTableModelArchiv.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				int row = e.getFirstRow();
				if (e.getColumn() == 5 || e.getColumn() == 0
						|| e.getColumn() == 1) {
					Task task = jtodoTableModelArchiv.getValueAtRow(row);
					controller.update(task);
				}
			}
		});

		TableColumn archivCol = archivTable.getColumnModel().getColumn(0);
		archivCol.setPreferredWidth(150);
		archivCol = archivTable.getColumnModel().getColumn(1);
		archivCol.setPreferredWidth(200);
		archivCol = archivTable.getColumnModel().getColumn(2);
		archivCol.setPreferredWidth(80);
		archivCol = archivTable.getColumnModel().getColumn(3);
		archivCol.setPreferredWidth(60);
		archivCol = archivTable.getColumnModel().getColumn(4);
		archivCol.setPreferredWidth(100);
		archivCol = archivTable.getColumnModel().getColumn(5);
		archivCol.setPreferredWidth(40);

		TableRowSorter<TableModel> archivSorter = new TableRowSorter<TableModel>(
				archivTable.getModel());
		archivTable.setRowSorter(archivSorter);

		JButton addTaskButton = new JButton("Task adden");

		addTaskButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Richtiger MVC ansatz, so müssen User Actions behandelt
				// werden.
				Date date = dateChooser.getDate();
				String taskName = newTaskNameField.getText();
				String taskDescription = newTaskDescriptionField.getText();
				Category cat = (Category) categoryBox.getSelectedItem();
				Priority prio = (Priority) prioBox.getSelectedItem();
				int catID = cat.getId();
				int prioID = prio.getId();

				Task task = new Task();
				task.setName(taskName);
				task.setDescription(taskDescription);
				task.setCategoryid(catID);
				task.setDate(date);
				task.setPriorityid(prioID);
				task.setModifiydate(new Date());
				task.setStatus(0);
				controller.addTask(task);
				clearTaskComponents();
			}

			private void clearTaskComponents() {
				newTaskNameField.setText(null);
				newTaskDescriptionField.setText(null);
				categoryBox.setSelectedIndex(0);
				prioBox.setSelectedIndex(0);
				dateChooser.setDate(new Date());
			}
		});

		tabs = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabs, BorderLayout.CENTER);

		TaskListPanel = new JPanel();
		TaskListPanel.setLayout(new BorderLayout(0, 0));

		NewTaskPanel = new JPanel();
		// absolut layout
		NewTaskPanel.setLayout(null);

		AboutPanel = new JPanel();
		AboutPanel.setLayout(null);

		JPanel ArchivPanel = new JPanel();
		ArchivPanel.setLayout(new BorderLayout(0, 0));

		tabs.addTab("Task Liste", TaskListPanel);
		tabs.addTab("New Task", NewTaskPanel);
		tabs.addTab("Archiv", ArchivPanel);
		tabs.addTab("About", AboutPanel);

		newTaskNameField = new JTextField(20);
		newTaskDescriptionField = new JTextField(20);
		newTaskNameLabel = new JLabel("Title: ");
		newTaskDescpritionLabel = new JLabel("Description: ");
		newTaskCategoryLabel = new JLabel("Category: ");
		newTaskPriorityLabel = new JLabel("Priority: ");
		newTaskDateLabel = new JLabel("Date: ");

		dateChooser = new JSpinnerDateEditor();
		dateChooser.setDate(new Date());
		dateChooser.setDateFormatString("dd/MM/yyyy");

		JTextField gitTextField = new JTextField(20);
		gitTextField.setText("https://github.com/barneyyy844/mdp_jtodo");
		gitTextField.setBounds(50, 50, 500, 25);
		JTextField wikiTextField = new JTextField(20);
		wikiTextField.setText("https://github.com/barneyyy844/mdp_jtodo/wiki");
		wikiTextField.setBounds(50, 100, 500, 25);
		AboutPanel.add(gitTextField);
		AboutPanel.add(wikiTextField);

		// x, y, width, height
		int firstX = 50;
		int secondX = 160;
		newTaskNameLabel.setBounds(firstX, 30, 100, 25);
		newTaskDescpritionLabel.setBounds(firstX, 60, 100, 25);
		newTaskCategoryLabel.setBounds(firstX, 90, 100, 25);
		newTaskPriorityLabel.setBounds(firstX, 120, 100, 25);
		newTaskDateLabel.setBounds(firstX, 150, 100, 25);
		newTaskNameField.setBounds(secondX, 30, 200, 25);
		newTaskDescriptionField.setBounds(secondX, 60, 200, 25);
		categoryBox.setBounds(secondX, 90, 120, 25);
		prioBox.setBounds(secondX, 120, 120, 25);
		dateChooser.setBounds(secondX, 150, 120, 25);
		addTaskButton.setBounds(100, 200, 100, 25);

		NewTaskPanel.add(newTaskNameLabel);
		NewTaskPanel.add(newTaskDescpritionLabel);
		NewTaskPanel.add(newTaskCategoryLabel);
		NewTaskPanel.add(newTaskPriorityLabel);
		NewTaskPanel.add(newTaskDateLabel);
		NewTaskPanel.add(newTaskNameField);
		NewTaskPanel.add(newTaskDescriptionField);
		NewTaskPanel.add(categoryBox);
		NewTaskPanel.add(prioBox);
		NewTaskPanel.add(dateChooser);
		NewTaskPanel.add(addTaskButton);

		JPanel outTaskListPanel = new JPanel(new BorderLayout());

		JPanel TaskListLeftPanel = new JPanel(new BorderLayout());
		JPanel TaskListCenterPanel = new JPanel();
		JPanel TaskListButtomPanel = new JPanel();
		outTaskListPanel.add(TaskListLeftPanel, BorderLayout.WEST);
		outTaskListPanel.add(TaskListCenterPanel, BorderLayout.CENTER);
		outTaskListPanel.add(TaskListButtomPanel, BorderLayout.SOUTH);

		JPanel topTaskListLeftPanel = new JPanel();
		JPanel buttomTaskListLeftPanel = new JPanel();
		TaskListLeftPanel.add(topTaskListLeftPanel, BorderLayout.NORTH);
		TaskListLeftPanel.add(buttomTaskListLeftPanel, BorderLayout.SOUTH);

		TaskListButtomPanel.add(taskCount);

		JPanel ArchivPanelCenterPanel = new JPanel();
		JPanel ArchivPanelButtomPanel = new JPanel();
		ArchivPanel.add(ArchivPanelCenterPanel, BorderLayout.CENTER);
		ArchivPanel.add(ArchivPanelButtomPanel, BorderLayout.SOUTH);

		ArchivPanelButtomPanel.add(archivCount);

		JScrollPane scrollPaneCatList = new JScrollPane(categoryTable);
		scrollPaneCatList
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneCatList.setPreferredSize(new Dimension(80, 100));
		topTaskListLeftPanel.add(scrollPaneCatList);

		JScrollPane scrollPanePrioList = new JScrollPane(priorityTable);
		scrollPanePrioList
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPanePrioList.setPreferredSize(new Dimension(80, 100));
		buttomTaskListLeftPanel.add(scrollPanePrioList);

		JScrollPane scrollPaneTaskList = new JScrollPane(taskTable);
		scrollPaneTaskList
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneTaskList.setPreferredSize(new Dimension(650, 200));
		TaskListCenterPanel.add(scrollPaneTaskList);

		JScrollPane scrollPaneArchivList = new JScrollPane(archivTable);
		scrollPaneArchivList
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneArchivList.setPreferredSize(new Dimension(650, 200));
		ArchivPanelCenterPanel.add(scrollPaneArchivList);

		TaskListPanel.add(outTaskListPanel);

		frame.setSize(800, 350);
		frame.addWindowListener(new openWindowListener());

		frame.setVisible(true);
	}

	public void SetController(GUIController controller) {
		this.controller = controller;
	}

	public void setModel(IDataHandler model) {
		this.model = model;
	}

	public void updateCategoryList(List<Category> categoryList) {

		for (Category category : categoryList) {
			categoryTableModel.addRow(new Object[] { category });
			categoryBoxModel.addElement(category);
		}
	}

	public void updatePriorityList(List<Priority> priorityList) {

		for (Priority priority : priorityList) {
			priorityTableModel.addRow(new Object[] { priority });
			prioBoxModel.addElement(priority);
		}
	}

	public void updateTaskList(List<Task> taskList) {
		jtodoTableModelTask.removeAllTasks();

		jtodoTableModelTask.addTaskList(taskList);
		taskCount.setText(jtodoTableModelTask.getRowCount() + " Tasks");
	}

	public void updateArchivList(List<Task> archivList) {
		jtodoTableModelArchiv.removeAllTasks();
		jtodoTableModelArchiv.addTaskList(archivList);
		archivCount.setText(jtodoTableModelArchiv.getRowCount()
				+ " abgeschlossene Tasks");
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
				List<Task> archivList = new ArrayList<Task>();
				List<Task> todoList = new ArrayList<Task>();
				for (Task task : taskList) {
					if (task.getStatus() == 1) {
						archivList.add(task);
					} else {
						todoList.add(task);
					}
				}
				updateTaskList(todoList);
				updateArchivList(archivList);
			}

			if (listElement instanceof Category) {
				List<Category> categoryList = list;
				this.jtodoTableModelTask.setCategoryList(list);
				this.jtodoTableModelArchiv.setCategoryList(list);
				updateCategoryList(categoryList);
			}

			if (listElement instanceof Priority) {
				List<Priority> priorityList = list;
				this.jtodoTableModelTask.setPriorityList(list);
				this.jtodoTableModelArchiv.setPriorityList(list);
				updatePriorityList(priorityList);
			}
		}
	}

	class openWindowListener extends WindowAdapter {
		public void windowOpened(WindowEvent e) {
			controller.getInitalData();
		}
	}

}
