package ch.zhaw.jtodo.controller;

import java.util.Date;
import java.util.List;
import java.util.Observer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;

import ch.zhaw.jtodo.dao.DAOFactory;
import ch.zhaw.jtodo.domain.Category;
import ch.zhaw.jtodo.domain.Task;
import ch.zhaw.jtodo.model.DataHandler;
import ch.zhaw.jtodo.model.IDataHandler;
import ch.zhaw.jtodo.view.JtodoView;

public class GUIController implements IGUIController{

	private IDataHandler model;
	private JtodoView view;

	public GUIController(IDataHandler model,JtodoView view) {
		this.model = model;
		this.view = view;
	}

	public void handleButtonAction() {
		// spaeter wird hier zusätzlich der insert in die db hinzugefuegt
		// System.out.println("Task wuerde hinzugefuegt");
		// diese methode wird spaeter benoetigt, um die taskliste an die view
		// weiterzugeben.
		//model.getTaskList();
		//view.update(model);
	}

	@Override
	public void addObserver(Observer view) {
		model.addNewObserver(view);
	}

	@Override
	public void getInitalData() {
		model.getAllTasks();
		model.getAllCategorys();
	}
	
	
	

	//not valid in here
	/*public void initDB(DefaultTableModel taskModel,
			DefaultListModel categoryListModel,
			DefaultComboBoxModel categoryBoxModel) {

		DataHandler handler = new DataHandler(new DAOFactory());

		List<Task> tasks = handler.getAllTasks();
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
		}*/
		
		/*not valid in here
		List<Category> cat = handler.getAllCategorys();
		for (int j = 0; j < cat.size(); j++) {
			String categoryName = cat.get(j).getName();
			categoryBoxModel.addElement(categoryName);
			categoryListModel.addElement(categoryName);
		}*/
}
