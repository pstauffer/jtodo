package ch.zhaw.jtodo.controller;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

import ch.zhaw.jtodo.dao.DAOFactory;
import ch.zhaw.jtodo.domain.Category;
import ch.zhaw.jtodo.domain.Task;
import ch.zhaw.jtodo.model.DataHandler;
import ch.zhaw.jtodo.model.JtodoModel;
import ch.zhaw.jtodo.view.JtodoView;

public class GUIController {

	private JtodoModel model;
	private JtodoView view;

	public GUIController() {
		model = new JtodoModel();
		view = new JtodoView(this);
	}

	public void handleButtonAction() {
		// spaeter wird hier zusätzlich der insert in die db hinzugefuegt
		// System.out.println("Task wuerde hinzugefuegt");
		model.countUp();
		// diese methode wird spaeter benoetigt, um die taskliste an die view
		// weiterzugeben.
		model.getTaskList();
		view.update(model);
	}

	public void initDB(DefaultTableModel taskModel,
			DefaultListModel categoryListModel,
			DefaultComboBoxModel categoryBoxModel) {

		DataHandler handler = new DataHandler(new DAOFactory());

		List<Task> tasks = handler.getAllTasks();
		for (int i = 0; i < tasks.size(); i++) {
			{
				String taskName = tasks.get(i).getName();
				String taskDesc = tasks.get(i).getDescription();
				taskModel.addRow(new Object[] { taskName, taskDesc });
			}
		}

		List<Category> cat = handler.getAllCategorys();
		for (int j = 0; j < cat.size(); j++) {
			String categoryName = cat.get(j).getName();
			categoryBoxModel.addElement(categoryName);
			categoryListModel.addElement(categoryName);
		}
	}

}
