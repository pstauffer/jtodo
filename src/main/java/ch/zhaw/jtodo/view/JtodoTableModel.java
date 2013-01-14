package ch.zhaw.jtodo.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import ch.zhaw.jtodo.domain.Category;
import ch.zhaw.jtodo.domain.Task;

public class JtodoTableModel extends AbstractTableModel {

	private String[] columnNames = { "Task", "Description", "Category",
			"Priority", "Date", "Status" };

	private ArrayList dataList = new ArrayList();
	private Map categoryList = new HashMap<Integer, String>();

	public JtodoTableModel() {

	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return dataList.size();
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Task task = (Task) dataList.get(row);

		switch (col) {
		case 0:
			return task.getName();
		case 1:
			return task.getDescription();
		case 2:
			String category = " ";
			try {
				category = (String) this.categoryList.get(task.getCategoryid());
			} catch (Exception e) {
				category = " ";
			}
			return category;
		case 3:
			return String.valueOf(task.getPriorityid());
		case 4:
			return task.getDate().toString();
		case 5:
			if (task.getStatus() == 1) {
				return new Boolean(true);
			} else if (task.getStatus() == 0) {
				return new Boolean(false);
			}
		default:
			return null;
		}
	}

	public Task getWidgetAt(int row) {
		return (Task) dataList.get(row);
	}

	public void addTask(Task task) {
		dataList.add(task);
		fireTableDataChanged();
	}

	public void addTaskList(List l) {
		dataList.addAll(l);
		fireTableDataChanged();
	}

	public Task removeTaskAt(int row) {
		Task task = (Task) dataList.remove(row);
		fireTableDataChanged();
		return task;
	}

	public void removeAllTasks() {
		this.dataList.clear();
	}

	public void setCategoryList(List<Category> categorys) {
		this.categoryList.clear();
		for (Category cat : categorys) {
			this.categoryList.put(cat.getId(), cat.getName());
		}
	}
	
	public void setValueAt(Object object,int row,int col){
		if(col == 5){
			Task task = (Task) this.dataList.get(row);
			Boolean bool = (Boolean)object;
			if(bool){
				task.setStatus(1);
			}else{
				task.setStatus(0);
			}
			fireTableDataChanged();
		}
	}

	public boolean isCellEditable(int row, int col) {
		// only the status column must be editable
		if (col == 5) {
			return true;
		} else {
			return false;
		}
	}
}
